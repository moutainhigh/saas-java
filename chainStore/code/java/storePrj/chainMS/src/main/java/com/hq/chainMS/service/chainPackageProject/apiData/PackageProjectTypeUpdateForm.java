package com.hq.chainMS.service.chainPackageProject.apiData;

import com.hq.chainMS.service.chainPackageProject.data.PackageProjectType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PackageProjectTypeUpdateForm {
	private String id;
	private String name;

	public static PackageProjectTypeUpdateForm newInstance() {
		return new PackageProjectTypeUpdateForm();
	}
	
	public void updatePackageProjectType(PackageProjectType data) {
		FastBeanCopyer.getInstance().copy(this, data);
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
