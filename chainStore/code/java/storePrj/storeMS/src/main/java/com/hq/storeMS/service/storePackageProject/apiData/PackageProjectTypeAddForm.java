package com.hq.storeMS.service.storePackageProject.apiData;

import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PackageProjectTypeAddForm {
	private long index;
	private String name;

	public static PackageProjectTypeAddForm newInstance() {
		return new PackageProjectTypeAddForm();
	}

	public PackageProjectType toPackageProjectType() {
		PackageProjectType data = PackageProjectType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(String.valueOf(index));
		return data;
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
