package com.hq.chainStore.service.storePackageProject.apiData;

public class PackageProjectRemoveForm {
	private String id;// storeId_index

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
