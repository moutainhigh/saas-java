package com.hq.chainMS.service.chainProduct.apiData;

import java.util.HashSet;
import java.util.Set;

public class ProductAllotForm {
	private String id;
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static ProductAllotForm newInstance() {
		return new ProductAllotForm();
	}

	public static ProductAllotForm newInstance(String idP, Set<Long> applyStoreIdsP) {
		ProductAllotForm data = new ProductAllotForm();
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
