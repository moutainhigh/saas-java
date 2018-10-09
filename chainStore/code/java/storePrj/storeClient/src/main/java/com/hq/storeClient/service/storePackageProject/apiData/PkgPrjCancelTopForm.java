package com.hq.storeClient.service.storePackageProject.apiData;

public class PkgPrjCancelTopForm {
	private String id;

	public static PkgPrjCancelTopForm newInstance() {
		PkgPrjCancelTopForm data = new PkgPrjCancelTopForm();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
