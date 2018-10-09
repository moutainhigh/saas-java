package com.hq.chainClient.service.chainClerk.apiData;

import java.util.HashSet;
import java.util.Set;

public class ChainAllotStoreForm {
	// 用户列表
	private long userId;
	// 店铺列表
	private Set<Long> storeIds = new HashSet<Long>();

	public static ChainAllotStoreForm newInstance() {
		return new ChainAllotStoreForm();
	}

	public long getUserId() {
		return userId;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
