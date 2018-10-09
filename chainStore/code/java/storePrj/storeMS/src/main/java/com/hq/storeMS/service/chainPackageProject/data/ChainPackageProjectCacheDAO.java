package com.hq.storeMS.service.chainPackageProject.data;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectCacheDAO {

	public static ChainPackageProjectCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectCacheDAO.class);
	}

	public ChainPackageProject get(long id) {
		return ChainPackageProjectRedisDAO.getInstance().get(id);
	}

	public void delete(ChainPackageProject target) {
		ChainPackageProjectRedisDAO.getInstance().delete(target.getId());
	}
	
}
