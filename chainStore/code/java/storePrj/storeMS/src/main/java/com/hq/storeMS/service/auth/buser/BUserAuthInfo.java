package com.hq.storeMS.service.auth.buser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreRolePermInfo;

public class BUserAuthInfo {
	
	private long buserId;
	
	/**
	 * Map<storeId, RolePermInfo>
	 */
	private Map<Long,StoreRolePermInfo> storeRPMap = new HashMap<Long, StoreRolePermInfo>();
	
	public static BUserAuthInfo newInstance(long buserIdP){
		BUserAuthInfo target = new BUserAuthInfo();
		target.buserId = buserIdP;
		
		return target; 
	}

	public long getBuserId() {
		return buserId;
	}

	public BUserAuthInfo addRolePermInfo(long storeId, StoreRolePermInfo rolePermInfo) {
		if(rolePermInfo != null){
			storeRPMap.put(storeId, rolePermInfo);
		}
		return this;
	}

	public Set<String> getRoleSet(){
		Set<String> rolesetTmp = new HashSet<String>();
		for (StoreRolePermInfo rpTmp : storeRPMap.values()) {
			rolesetTmp.addAll(rpTmp.getRoleSet());
		}
		return rolesetTmp;
	}
	
	public Set<String> getPermSet(){
		Set<String> permSetTmp = new HashSet<String>();
		for (StoreRolePermInfo rpTmp : storeRPMap.values()) {
			permSetTmp.addAll(rpTmp.getPermSet());
		}
		return permSetTmp;
	}

	public Map<Long, StoreRolePermInfo> getStoreRPMap() {
		return storeRPMap;
	}
	
	
}
