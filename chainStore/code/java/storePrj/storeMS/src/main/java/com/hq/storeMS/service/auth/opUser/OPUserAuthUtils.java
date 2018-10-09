package com.hq.storeMS.service.auth.opUser;

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
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import com.hq.storeMS.service.auth.sessionDao.SessionRedisDao;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminPermEnum;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class OPUserAuthUtils {
	
	public static OPUserAuthUtils getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserAuthUtils.class);
	}

	final private String HEADER_ACCESS_TOKEN_NAME = "access_token";

	private DefaultSecurityManager securityManager;

	public OPUserAuthUtils() {
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
		securityManager.setRealms(Arrays.asList((Realm) new OPUserRealm()));
		
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie("JID"));
		sessionManager.setSessionDAO(SessionRedisDao.newInstance("opUser"));
		
		securityManager.setSessionManager(sessionManager);
	}
	
	public String login(String name, String password) {
		Subject subject = (new Subject.Builder(securityManager)).buildSubject();
		ThreadContext.bind(subject);
		try {
			AuthenticationToken token = new UsernamePasswordToken(name, password);
			subject.login(token);
			Serializable sessionId = subject.getSession().getId();
			String assesToken = TokenHelper.getInstance().sessionIdToToken(sessionId);
			return assesToken;
		} catch (AuthenticationException e) {
			MainLog.info(LogModule.OPUser, "phone", "登陆失败");
			return null;
		}
	}

	public void checkPermission(OPAdminPermEnum permEnum) {
		Subject subject = ThreadContext.getSubject();
		subject.checkPermission(permEnum.getPerm());
	}
	public boolean isPermitted(OPAdminPermEnum permEnum) {
		Subject subject = ThreadContext.getSubject();
		return subject.isPermitted(permEnum.getPerm());
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
