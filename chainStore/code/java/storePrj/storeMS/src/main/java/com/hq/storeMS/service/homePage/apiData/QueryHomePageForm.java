package com.hq.storeMS.service.homePage.apiData;

import java.util.HashSet;
import java.util.Set;

public class QueryHomePageForm {
	// 店铺ID
	private long storeId;
	// 用户ID
	private long buserId;

	// 数据项集合 HomePageItemEnum
	private Set<Integer> items = new HashSet<Integer>();

	public static QueryHomePageForm newInstance() {
		QueryHomePageForm data = new QueryHomePageForm();
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public Set<Integer> getItems() {
		return items;
	}

	public void setItems(Set<Integer> items) {
		this.items = items;
	}

}
