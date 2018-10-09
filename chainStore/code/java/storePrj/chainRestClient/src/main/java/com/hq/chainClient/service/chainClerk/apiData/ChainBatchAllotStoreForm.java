package com.hq.chainClient.service.chainClerk.apiData;

import java.util.HashSet;
import java.util.Set;

public class ChainBatchAllotStoreForm {
	// 用户列表
	private Set<Long> userIds = new HashSet<Long>();
	// 店铺列表
	private Set<Long> storeIds = new HashSet<Long>();

	public static ChainBatchAllotStoreForm newInstance() {
		return new ChainBatchAllotStoreForm();
	}
	
	public Set<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<Long> userIds) {
		this.userIds = userIds;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

}
