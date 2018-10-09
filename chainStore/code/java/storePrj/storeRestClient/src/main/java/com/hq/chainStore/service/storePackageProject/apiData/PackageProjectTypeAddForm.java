package com.hq.chainStore.service.storePackageProject.apiData;

public class PackageProjectTypeAddForm {
	private long index;
	private String name;

	public static PackageProjectTypeAddForm newInstance() {
		return new PackageProjectTypeAddForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

}
