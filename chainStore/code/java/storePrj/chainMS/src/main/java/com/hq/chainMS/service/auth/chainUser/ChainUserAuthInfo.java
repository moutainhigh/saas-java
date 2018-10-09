package com.hq.chainMS.service.auth.chainUser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.chainMS.service.chainClerk.data.adminRole.RolePermInfo;

public class ChainUserAuthInfo {
	private long userId;
	// Map<chainId, RolePermInfo>
	private Map<Long, RolePermInfo> storeRPMap = new HashMap<Long, RolePermInfo>();
	
	public static ChainUserAuthInfo newInstance(long chainUserIdP){
		ChainUserAuthInfo target = new ChainUserAuthInfo();
		target.userId = chainUserIdP;
		return target; 
	}

	public ChainUserAuthInfo addRolePermInfo(long chainId, RolePermInfo rolePermInfo) {
		if(rolePermInfo != null){
			storeRPMap.put(chainId, rolePermInfo);
		}
		return this;
	}

	public Set<String> getRoleSet(){
		Set<String> rolesetTmp = new HashSet<String>();
		for (RolePermInfo rpTmp : storeRPMap.values()) {
			rolesetTmp.addAll(rpTmp.getRoleSet());
		}
		return rolesetTmp;
	}
	
	public Set<String> getPermSet(){
		Set<String> permSetTmp = new HashSet<String>();
		for (RolePermInfo rpTmp : storeRPMap.values()) {
			permSetTmp.addAll(rpTmp.getPermSet());
		}
		return permSetTmp;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
