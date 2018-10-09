package com.hq.chainStore.service.storePackageProject.apiData;

public class PackageProjectTypeUpdateForm {
	private String id;
	private String name;

	public static PackageProjectTypeUpdateForm newInstance() {
		return new PackageProjectTypeUpdateForm();
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

}
