package com.hq.storeManagerMS.service.auth.muser;

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

import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.auth.sessionDao.SessionRedisDao;
import com.hq.storeManagerMS.service.muser.bs.MUserMgr;
import com.hq.storeManagerMS.service.muser.data.MUserRO;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminPermEnum;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserAuthUtils {
	
	public static MUserAuthUtils getInstance(){
		return HotSwap.getInstance().getSingleton(MUserAuthUtils.class);
	}

	final private String HEADER_ACCESS_TOKEN_NAME = "access_token";
	final private String SESSION_USERID = "session_userId";
	final private String SESSION_USER_ACCOUNT = "session_account";
	
	private DefaultSecurityManager securityManager;
	
	public MUserAuthUtils() {
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
		securityManager.setRealms(Arrays.asList((Realm) new MUserRealm()));
		
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie("JID"));
		sessionManager.setSessionDAO(SessionRedisDao.newInstance("muser"));
        sessionManager.setGlobalSessionTimeout(ServerConstants.SESSION_EFFECTIVE_TIME);
        securityManager.setSessionManager(sessionManager);
	}
	
	public String login(String account, String password) {
		Subject subject = (new Subject.Builder(securityManager)).buildSubject();
		ThreadContext.bind(subject);
		try {
			AuthenticationToken token = new UsernamePasswordToken(account, password);
			subject.login(token);
			MUserRO muserRO = MUserMgr.getInstance().findByAccount(account);
			subject.getSession(true).setAttribute(SESSION_USERID, muserRO.getId());
			subject.getSession(true).setAttribute(SESSION_USER_ACCOUNT, account);
			Serializable sessionId = subject.getSession().getId();
			String assesToken = TokenHelper.getInstance().sessionIdToToken(sessionId);
			return assesToken;
		} catch (AuthenticationException e) {
			MainLog.info(LogModule.MUser, "phone", "登陆失败");
			return null;
		}
	}
	public void checkMUser(long muserId){
		Subject subject = ThreadContext.getSubject();
		Long session_userId = (Long) subject.getSession().getAttribute(SESSION_USERID);
		if(session_userId != muserId){
			final String infoPattern = "Subject does not have permission, login muserId is {}, muserId input to check is:{}";
			throw new UnauthorizedException(StringFormatUtil.format(infoPattern, session_userId,muserId));
		}
	}
	
	public void checkPermission(long storeId, MUserAdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return;
		}
		subject.checkPermission(permEnum.getPerm());
	}

	public boolean isPermitted(long storeId, MUserAdminPermEnum permEnum ) {
		Subject subject = ThreadContext.getSubject();
		if(subject == null) {//第三方微服务
			return true;
		}
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
