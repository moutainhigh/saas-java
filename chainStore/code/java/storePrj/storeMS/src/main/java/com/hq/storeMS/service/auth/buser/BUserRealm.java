package com.hq.storeMS.service.auth.buser;

import java.util.HashSet;
import java.util.Set;

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

import com.hq.storeMS.common.util.CollectionUtil;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buserRole.bs.BuserRoleMgr;
import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;


public class BUserRealm extends AuthorizingRealm {

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String priAccountNum = String.valueOf( principals.getPrimaryPrincipal() );
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		BUserAuthInfo authInfo = BUserQueryMgr.getInstance().findRolePermInfoByAccount(priAccountNum);
		authorizationInfo.setRoles(authInfo.getRoleSet());
		authorizationInfo.setStringPermissions(getPermSet(authInfo));
		return authorizationInfo;
	}
	
	private Set<String> getPermSet(BUserAuthInfo authInfo){
		Set<Long> storeIds = authInfo.getStoreRPMap().keySet();
		
		//老板会员等级的权限
		Set<String> chainPermSet = new HashSet<String>();
		for (Long storeId : storeIds) {
			BuserRole buserRole = BuserRoleMgr.getInstance().getBossRoleByStoreId(storeId);
			if(buserRole == null) {
				continue;
			}
			Set<Integer> permIdxSet = buserRole.getVipContent().getPermSet();
			for (Integer permIndex : permIdxSet) {
				chainPermSet.add(StoreAdminPermEnum.valueOf(permIndex).getPerm(storeId));
			}
		}
		
		//本地店铺员工的权限
		Set<String> storePermSet = authInfo.getPermSet();
		StoreAdminPermEnum[] adminPermEnums = StoreAdminPermEnum.values();
		for (Long storeId : storeIds) {
			if(storePermSet.contains(StoreAdminPermEnum.BOSS.getPerm(storeId))) {
				for (StoreAdminPermEnum storeAdminPermEnum : adminPermEnums) {
					storePermSet.add(storeAdminPermEnum.getPerm(storeId));
				}
			}
		}
		
		return CollectionUtil.retainAll(chainPermSet, storePermSet);
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken tokenp = (UsernamePasswordToken)token;
		String priAccountNum = String.valueOf(tokenp.getPrincipal());
		BUser user = BUserQueryMgr.getInstance().findByPriAccountNum(priAccountNum);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getPriAccountNum(), 
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=account+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	

}
