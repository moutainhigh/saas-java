package com.hq.chainClient.service.chainClerk.apiData;

public class AdminRoleRemoveForm {
	private long chainId;
	private int roleId;

	public static AdminRoleRemoveForm newInstance() {
		return new AdminRoleRemoveForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
