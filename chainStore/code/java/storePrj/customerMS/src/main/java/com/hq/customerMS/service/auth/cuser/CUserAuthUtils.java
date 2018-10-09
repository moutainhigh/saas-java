package com.hq.customerMS.service.auth.cuser;

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

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.auth.sessionDao.SessionRedisDao;
import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserAuthUtils {
	
	public static CUserAuthUtils getInstance(){
		return HotSwap.getInstance().getSingleton(CUserAuthUtils.class);
	}

	final private String HEADER_ACCESS_TOKEN_NAME = "access_token";
	final private String SESSION_USERID = "session_userId";

	private DefaultSecurityManager securityManager;

	public CUserAuthUtils() {
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
		securityManager.setRealms(Arrays.asList((Realm) new CUserRealm()));
		
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie("JID"));
		sessionManager.setSessionDAO(SessionRedisDao.newInstance("cuser"));
        sessionManager.setGlobalSessionTimeout(ServerConstants.SESSION_EFFECTIVE_TIME);
		securityManager.setSessionManager(sessionManager);
	}
	
	/**
	 * 
	 * @param user 系统数据库存储的数据
	 * @param encryptPassword 登录时，前端传过来的文明密码 加密后的字符串
	 * @return
	 */
	public String loginCuser(CUser user, String encryptPassword) {
		Subject subject = (new Subject.Builder(securityManager)).buildSubject();
		ThreadContext.bind(subject);
		try {
			AuthenticationToken token = new UsernamePasswordToken(user.getPriAccountNum(), encryptPassword);
			subject.login(token);
			subject.getSession(true).setAttribute(SESSION_USERID, user.getId());
			Serializable sessionId = subject.getSession().getId();
			String assesToken = TokenHelper.getInstance().sessionIdToToken(sessionId);
			return assesToken;
		} catch (AuthenticationException e) {
			MainLog.info(LogModule.CUser, "CUserAuthUtils[loginCuser]", "登陆失败");
			return null;
		}
	}
	
	public void checkUser(long userId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		if(session_userId != userId){
			final String infoPattern = "Subject does not have permission, login userId is {}, userId input to check is:{}";
			throw new UnauthorizedException(StringFormatUtil.format(infoPattern, session_userId,userId));
		}
	}
	
	public boolean isUser(long userId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		return session_userId == userId;
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
