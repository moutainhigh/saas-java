package com.hq.chainClient.service.chainPackageProject.apiData;

import java.util.HashSet;
import java.util.Set;

public class PackageProjectAllotForm {
	private String id;
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static PackageProjectAllotForm newInstance() {
		return new PackageProjectAllotForm();
	}

	public static PackageProjectAllotForm newInstance(String idP, Set<Long> applyStoreIdsP) {
		PackageProjectAllotForm data = new PackageProjectAllotForm();
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
