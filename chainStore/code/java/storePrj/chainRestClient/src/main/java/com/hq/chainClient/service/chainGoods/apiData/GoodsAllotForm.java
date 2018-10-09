package com.hq.chainClient.service.chainGoods.apiData;

import java.util.HashSet;
import java.util.Set;

public class GoodsAllotForm {
	private String id;
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static GoodsAllotForm newInstance() {
		return new GoodsAllotForm();
	}

	public static GoodsAllotForm newInstance(String idP, Set<Long> applyStoreIdsP) {
		GoodsAllotForm data = new GoodsAllotForm();
		data.id = idP;
		data.applyStoreIds = applyStoreIdsP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}
}
