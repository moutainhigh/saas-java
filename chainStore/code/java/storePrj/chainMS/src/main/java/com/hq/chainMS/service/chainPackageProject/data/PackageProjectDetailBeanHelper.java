package com.hq.chainMS.service.chainPackageProject.data;

import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailBeanHelper {
	public static PackageProjectDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailBeanHelper.class);
	}
}
