package com.hq.storeManagerMS.service.auth.muser;

import java.util.Set;

import com.hq.storeManagerMS.service.muserAdminRole.data.MUserRolePermInfo;

public class MUserAuthInfo {

	private long muserId;

	private MUserRolePermInfo rolePermInfo;

	public static MUserAuthInfo newInstance(long muserIdP, MUserRolePermInfo rolePermInfo) {
		MUserAuthInfo target = new MUserAuthInfo();
		target.muserId = muserIdP;
		target.rolePermInfo = rolePermInfo;
		return target;
	}

	public long getMuserId() {
		return muserId;
	}

	public Set<String> getRoleSet() {
		return rolePermInfo.getRoleSet();
	}

	public Set<String> getPermSet() {
		return rolePermInfo.getPermSet();
	}

	public MUserRolePermInfo getRolePermInfo() {
		return rolePermInfo;
	}

}
