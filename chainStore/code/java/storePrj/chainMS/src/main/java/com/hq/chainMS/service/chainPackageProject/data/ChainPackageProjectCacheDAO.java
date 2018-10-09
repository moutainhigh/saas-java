package com.hq.chainMS.service.chainPackageProject.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainPackageProjectCacheDAO {

	public static ChainPackageProjectCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectCacheDAO.class);
	}

	public void save(ChainPackageProject target) {
		ChainPackageProjectRedisDAO.getInstance().save(target);
	}
	
	public ChainPackageProject get(long id) {
		return ChainPackageProjectRedisDAO.getInstance().get(id);
	}

	public void delete(ChainPackageProject target) {
		ChainPackageProjectRedisDAO.getInstance().delete(target.getId());
	}
	
}
