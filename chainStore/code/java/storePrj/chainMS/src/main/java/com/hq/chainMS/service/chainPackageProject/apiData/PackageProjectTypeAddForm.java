package com.hq.chainMS.service.chainPackageProject.apiData;

import com.hq.chainMS.service.chainPackageProject.data.PackageProjectType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PackageProjectTypeAddForm {
	private long index;
	private String name;

	public static PackageProjectTypeAddForm newInstance() {
		return new PackageProjectTypeAddForm();
	}

	public PackageProjectType toPackageProjectType(long chainId) {
		PackageProjectType data = PackageProjectType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(PackageProjectType.generateId(chainId, index));
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
