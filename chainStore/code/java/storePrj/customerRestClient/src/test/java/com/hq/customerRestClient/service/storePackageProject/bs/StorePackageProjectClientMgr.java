package com.hq.customerRestClient.service.storePackageProject.bs;

import com.hq.customerRestClient.service.storePackageProject.data.StorePackageProject;
import com.hq.customerRestClient.service.storePackageProject.data.StorePackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectClientMgr {

	public static StorePackageProjectClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectClientMgr.class);
	}

	public StorePackageProject get(long storeId) {
		return StorePackageProjectDAO.getInstance().get(storeId);
	}

}
