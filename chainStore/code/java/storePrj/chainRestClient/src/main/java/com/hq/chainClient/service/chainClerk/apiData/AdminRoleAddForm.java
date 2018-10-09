package com.hq.chainClient.service.chainClerk.apiData;

import com.hq.chainClient.service.chainClerk.data.adminRole.AdminRole;

public class AdminRoleAddForm {
	private long chainId;
	private AdminRole role;

	public static AdminRoleAddForm newInstance() {
		return new AdminRoleAddForm();
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
