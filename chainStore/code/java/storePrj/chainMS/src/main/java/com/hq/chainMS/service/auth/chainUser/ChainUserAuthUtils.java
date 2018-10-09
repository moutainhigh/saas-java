package com.hq.chainMS.service.auth.chainUser;

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

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.sessionDao.SessionRedisDao;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserAuthUtils {
	
	public static ChainUserAuthUtils getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserAuthUtils.class);
	}

	final private String HEADER_ACCESS_TOKEN_NAME = "access_token";
	final private String SESSION_USERID = "session_userId";
	final private String SESSION_USERPHONE = "session_userPhone";
	
	private DefaultSecurityManager securityManager;
	
	public ChainUserAuthUtils() {
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
		securityManager.setRealms(Arrays.asList((Realm) new ChainUserRealm()));
		
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie("JID"));
		sessionManager.setSessionDAO(SessionRedisDao.newInstance("chainUser"));
        sessionManager.setGlobalSessionTimeout(ServerConstants.SESSION_EFFECTIVE_TIME);
        securityManager.setSessionManager(sessionManager);
	}
	
	public String login(Long phone, String password) {
		Subject subject = (new Subject.Builder(securityManager)).buildSubject();
		ThreadContext.bind(subject);
		try {
			AuthenticationToken token = new UsernamePasswordToken(phone.toString(), password);
			subject.login(token);
			ChainUser ChainUser = ChainUserMgr.getInstance().findByPhone(phone);
			subject.getSession(true).setAttribute(SESSION_USERID, ChainUser.getId());
			subject.getSession(true).setAttribute(SESSION_USERPHONE, phone);
			Serializable sessionId = subject.getSession().getId();
			String assesToken = TokenHelper.getInstance().sessionIdToToken(sessionId);
			return assesToken;
		} catch (AuthenticationException e) {
			MainLog.info(LogModule.ChainUser, "phone", "登陆失败");
			return null;
		}
	}
	
	public void checkChainUser(long userId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		if(session_userId != userId){
			final String infoPattern = "Subject does not have permission, login ChainUserId is {}, ChainUserId input to check is:{}";
			throw new UnauthorizedException(StringFormatUtil.format(infoPattern, session_userId,userId));
		}
	}
	
	public void checkPermission(long chainId, AdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject==null) {//第三方微服务进来
			return ;
		}
		subject.checkPermission(permEnum.getPerm(chainId));
	}

	public boolean isPermitted(long chainId, AdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject==null) {//第三方微服务进来
			return true;
		}
		return subject.isPermitted(permEnum.getPerm(chainId));
	}
	
	public Long getSessionChainUserId(){
		Subject subject = ThreadContext.getSubject();
		return (Long) subject.getSession().getAttribute(SESSION_USERID);
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
