package com.hq.storeMS.service.storePackageProject.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StorePackageProjectCacheDAO {

	public static StorePackageProjectCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectCacheDAO.class);
	}

	public void save(StorePackageProject target) {
		StorePackageProjectRedisDAO.getInstance().save(target);
	}
	
	public StorePackageProject get(long id) {
		return StorePackageProjectRedisDAO.getInstance().get(id);
	}

	public void delete(StorePackageProject target) {
		StorePackageProjectRedisDAO.getInstance().delete(target.getId());
	}
}
