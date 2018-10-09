package com.hq.chainClient.service.chainPackageProject.apiData;

public class PackageProjectRemoveForm {
	private String id;

	public static PackageProjectRemoveForm newInstance() {
		return new PackageProjectRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
