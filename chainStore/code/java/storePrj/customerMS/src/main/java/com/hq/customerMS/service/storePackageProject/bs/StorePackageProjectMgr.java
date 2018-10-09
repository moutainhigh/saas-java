package com.hq.customerMS.service.storePackageProject.bs;

import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectMgr {

	public static StorePackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectMgr.class);
	}
	
	public StorePackageProject getByStoreId(long storeId) {
		return StorePackageProjectDataHolder.getInstance().get(storeId);
	}
}
