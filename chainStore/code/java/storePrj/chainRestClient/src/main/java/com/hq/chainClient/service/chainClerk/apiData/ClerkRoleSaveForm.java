package com.hq.chainClient.service.chainClerk.apiData;

import java.util.HashSet;
import java.util.Set;

public class ClerkRoleSaveForm {
	private long chainId;
	private long userId;
	private Set<Integer> roleIds = new HashSet<Integer>();

	public static ClerkRoleSaveForm newInstance() {
		return new ClerkRoleSaveForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}

}
