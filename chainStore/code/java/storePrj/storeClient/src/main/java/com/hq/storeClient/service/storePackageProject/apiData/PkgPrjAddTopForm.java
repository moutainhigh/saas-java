package com.hq.storeClient.service.storePackageProject.apiData;

public class PkgPrjAddTopForm {
	private String id;

	public static PkgPrjAddTopForm newInstance() {
		PkgPrjAddTopForm data = new PkgPrjAddTopForm();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
