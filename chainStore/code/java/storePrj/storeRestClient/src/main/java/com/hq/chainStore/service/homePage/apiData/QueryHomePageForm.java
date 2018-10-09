package com.hq.chainStore.service.homePage.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.common.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class QueryHomePageForm {
	// 店铺ID
	private long storeId;
	// 用户ID
	private long buserId;

	// 数据项集合
	private Set<Integer> items = new HashSet<Integer>();

	public static QueryHomePageForm newInstance() {
		QueryHomePageForm data = new QueryHomePageForm();
		return data;
	}
	
	public ReqMap toReqMap(){
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", this.storeId).add("buserId", this.buserId)
			.add("items", StringUtils4Client.join(this.items, ","));
		return reqMap;
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
