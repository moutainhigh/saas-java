package com.hq.chainMS.service.buserMessage.apiData;

import java.util.HashSet;
import java.util.Set;

public class ProductMessageForm {
	//ID
	private String id;
	// 名称
	private String name;
	// 店铺描述
	private Set<Long> storeIds = new HashSet<Long>();

	public static ProductMessageForm newInstance() {
		return new ProductMessageForm();
	}
	
	public static ProductMessageForm newInstance(String idP, String nameP, Set<Long> storeIdsP) {
		ProductMessageForm data = newInstance();
		data.id=idP;
		data.name=nameP;
		data.storeIds=storeIdsP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

}
