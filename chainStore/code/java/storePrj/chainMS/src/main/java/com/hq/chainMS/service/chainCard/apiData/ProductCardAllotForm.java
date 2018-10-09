package com.hq.chainMS.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

public class ProductCardAllotForm {
	private String id;
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static ProductCardAllotForm newInstance() {
		return new ProductCardAllotForm();
	}

	public static ProductCardAllotForm newInstance(String idP, Set<Long> applyStoreIdsP) {
		ProductCardAllotForm data = new ProductCardAllotForm();
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
