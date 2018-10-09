package com.hq.storeMS.service.auth.buser;

import java.io.Serializable;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.sessionDao.SessionRedisDao;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserAuthUtils {
	
	public static BUserAuthUtils getInstance(){
		return HotSwap.getInstance().getSingleton(BUserAuthUtils.class);
	}

	final private String HEADER_ACCESS_TOKEN_NAME = "access_token";
	final private String SESSION_USERID = "session_userId";
	
	private DefaultSecurityManager securityManager;
	
	public BUserAuthUtils() {
		securityManager = new DefaultSecurityManager();
		// 设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);

		// 设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);

		// 设置Realm
		securityManager.setRealms(Arrays.asList((Realm) new BUserRealm()));
		
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie("JID"));
		sessionManager.setSessionDAO(SessionRedisDao.newInstance("buser"));
        sessionManager.setGlobalSessionTimeout(ServerConstants.SESSION_EFFECTIVE_TIME);
        securityManager.setSessionManager(sessionManager);
	}
	
	public String login(BUser buser, String encryptPassword) {
		Subject subject = (new Subject.Builder(securityManager)).buildSubject();
		ThreadContext.bind(subject);
		try {
			AuthenticationToken token = new UsernamePasswordToken(buser.getPriAccountNum(), encryptPassword);
			subject.login(token);
			subject.getSession(true).setAttribute(SESSION_USERID, buser.getId());
			Serializable sessionId = subject.getSession().getId();
			String assesToken = TokenHelper.getInstance().sessionIdToToken(sessionId);
			return assesToken;
		} catch (AuthenticationException e) {
			MainLog.info(LogModule.BUser, buser.getPriAccountNum(), "登陆失败");
			return null;
		}
	}
	
	public void checkBuser(long buserId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		if(session_userId != buserId){
			final String infoPattern = "Subject does not have permission, login buserId is {}, buserId input to check is:{}";
			throw new UnauthorizedException(StringFormatUtil.format(infoPattern, session_userId,buserId));
		}
	}
	
	public void checkStoreClerk(long storeId){
		BUser buser = getSessionBUser();
		if(!buser.getStoreIdSet().contains(storeId)){
			final String infoPattern = "Subject does not have permission, login user is not store clerk:{}-{}";
			throw new UnauthorizedException(StringFormatUtil.format(infoPattern, buser.getId(), storeId));
		}
	}
	
	public boolean isBuser(long buserId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		return session_userId == buserId;
	}
	
	public void checkPermission(long storeId, StoreAdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return;
		}
		subject.checkPermission(permEnum.getPerm(storeId));
	}

	public boolean isPermitted(long storeId, StoreAdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return true;
		}
		return subject.isPermitted(permEnum.getPerm(storeId));
	}
	
	/**
	 * getSessionBUser:(根据Session,获取BUser信息)
	 * @return BUser
	 */
	public BUser getSessionBUser(){
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return null;
		}
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		return BUserQueryMgr.getInstance().getSimple(session_userId);
	}
	
	/**
	 * getSessionBUserId:(根据Session,获取BUserId信息)
	 * @return buserId
	 */
	public Long getSessionBUserId(){
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return null;
		}
		return (Long) subject.getSession().getAttribute(SESSION_USERID);
	}
	
	/**
	 * 
	 * isPermitteds:(检测登陆者是否有指定的权限[至少有一种]). <br/>   
	 *  
	 * @author kevin
	 * @param storeId
	 * @param permEnum
	 * @return  
	 * @since JDK 1.6
	 */
	public boolean isPermitteds(long storeId, StoreAdminPermEnum...permEnums) {
		boolean b = false;
		for (StoreAdminPermEnum storeAdminPermEnum : permEnums) {
			b = isPermitted(storeId, storeAdminPermEnum);
			if(b){
				break;
			}
		}
		return b;
	}

	public Subject removeSubjectTL() {
		return ThreadContext.unbindSubject();
	}
	
	public Subject setSubjectTL(HttpServletRequest req){
		Subject subject = null;
		String accessToken = req.getHeader(HEADER_ACCESS_TOKEN_NAME);
		if(StringUtils.isNotBlank(accessToken)){
			Serializable sessionId = TokenHelper.getInstance().tokenToSessionId(accessToken);
			subject = new Subject.Builder(securityManager).sessionId(sessionId).buildSubject();
			if(!subject.isAuthenticated()) {
				return null;
			}
			subject.getSession(true);
			ThreadContext.bind(subject);
		}
		return subject;
	}
	
}
