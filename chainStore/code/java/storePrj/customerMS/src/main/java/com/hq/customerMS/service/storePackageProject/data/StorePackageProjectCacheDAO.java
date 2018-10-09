package com.hq.customerMS.service.storePackageProject.data;

import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectCacheDAO {

	public static StorePackageProjectCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectCacheDAO.class);
	}
	
	public StorePackageProject get(long id) {
		return StorePackageProjectRedisDAO.getInstance().get(id);
	}
}
