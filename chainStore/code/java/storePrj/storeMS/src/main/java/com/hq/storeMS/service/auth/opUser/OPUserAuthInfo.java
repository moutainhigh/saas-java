package com.hq.storeMS.service.auth.opUser;

import java.util.Set;

import com.hq.storeMS.service.opuser.data.adminRole.OPRolePermInfo;

public class OPUserAuthInfo {
	
	private long opId;
	
	/**
	 * Map<storeId, RolePermInfo>
	 */
	private OPRolePermInfo rolePermInfo;
	
	public static OPUserAuthInfo newInstance(long opIdP){
		OPUserAuthInfo target = new OPUserAuthInfo();
		target.opId = opIdP;
		
		return target; 
	}


	public long getOpId() {
		return opId;
	}

	public OPRolePermInfo getRolePermInfo() {
		return rolePermInfo;
	}


	public void setRolePermInfo(OPRolePermInfo rolePermInfo) {
		this.rolePermInfo = rolePermInfo;
	}


	public Set<String> getRoleSet(){
		return rolePermInfo.getRoleSet();
	}
	
	public Set<String> getPermSet(){
		return rolePermInfo.getPermSet();
	}
	
	
	
}
