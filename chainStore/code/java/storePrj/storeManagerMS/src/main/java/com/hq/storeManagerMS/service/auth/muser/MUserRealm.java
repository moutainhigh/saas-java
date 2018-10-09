package com.hq.storeManagerMS.service.auth.muser;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.hq.storeManagerMS.service.muser.bs.MUserMgr;
import com.hq.storeManagerMS.service.muser.data.MUserRO;


public class MUserRealm extends AuthorizingRealm {

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = getAccount( principals.getPrimaryPrincipal() );
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		MUserAuthInfo authInfo = MUserMgr.getInstance().findRolePermInfoByAccount(account);
		authorizationInfo.setRoles(authInfo.getRoleSet());
		authorizationInfo.setStringPermissions(authInfo.getPermSet());
		return authorizationInfo;
	}

	private String getAccount(Object principal){
		return String.valueOf(principal);
	}
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken tokenp = (UsernamePasswordToken)token;
		String account = getAccount(tokenp.getPrincipal());
		MUserRO user = MUserMgr.getInstance().findByAccount(account);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getAccount(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	

}
