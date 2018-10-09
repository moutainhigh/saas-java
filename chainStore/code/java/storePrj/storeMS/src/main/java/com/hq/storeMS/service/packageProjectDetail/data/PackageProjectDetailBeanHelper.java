package com.hq.storeMS.service.packageProjectDetail.data;

import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailBeanHelper {
	public static PackageProjectDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailBeanHelper.class);
	}
}
