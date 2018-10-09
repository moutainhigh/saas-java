package com.hq.chainClient.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

public class MemberCardAllotForm {
	private String id;
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static MemberCardAllotForm newInstance() {
		return new MemberCardAllotForm();
	}

	public static MemberCardAllotForm newInstance(String idP, Set<Long> applyStoreIdsP) {
		MemberCardAllotForm data = new MemberCardAllotForm();
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
