package com.hq.chainMS.service.chainPackageProject.apiData;

public class PackageProjectTypeRemoveForm {
	private String id;

	public static PackageProjectTypeRemoveForm newInstance() {
		return new PackageProjectTypeRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
