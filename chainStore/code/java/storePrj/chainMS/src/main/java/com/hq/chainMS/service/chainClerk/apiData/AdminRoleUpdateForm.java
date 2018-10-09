package com.hq.chainMS.service.chainClerk.apiData;

import com.hq.chainMS.service.chainClerk.data.adminRole.AdminRole;

public class AdminRoleUpdateForm {
	private long chainId;
	private AdminRole role;
	
	public static AdminRoleUpdateForm newInstance(){
		return new AdminRoleUpdateForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public AdminRole getRole() {
		return role;
	}

	public void setRole(AdminRole role) {
		this.role = role;
	}
	
}
