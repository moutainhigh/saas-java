package com.hq.storeMS.service.auth.opUser;

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

import com.hq.storeMS.service.opuser.bs.OPUserMgr;
import com.hq.storeMS.service.opuser.data.OPUser;


public class OPUserRealm extends AuthorizingRealm {

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Long phone = getPhone( principals.getPrimaryPrincipal() );
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		OPUserAuthInfo authInfo = OPUserMgr.getInstance().findRolePermInfoByPhone(phone);
		
		authorizationInfo.setRoles(authInfo.getRoleSet());
		authorizationInfo.setStringPermissions(authInfo.getPermSet());
		return authorizationInfo;
	}

	private Long getPhone(Object principal){
		String phone_str = String.valueOf(principal);
		
		return Long.valueOf(phone_str);
		
	}
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken tokenp = (UsernamePasswordToken)token;
		
		Long phone = getPhone(tokenp.getPrincipal());
		OPUser user = OPUserMgr.getInstance().findByPhone(phone);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getPhone(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	

}
