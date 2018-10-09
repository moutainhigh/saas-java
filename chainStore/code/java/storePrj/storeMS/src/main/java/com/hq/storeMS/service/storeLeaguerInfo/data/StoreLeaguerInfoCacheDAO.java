package com.hq.storeMS.service.storeLeaguerInfo.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoCacheDAO {

	public static StoreLeaguerInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoCacheDAO.class);
	}

	public void save(StoreLeaguerInfo target) {
		StoreLeaguerInfoRedisDAO.getInstance().save(target);
	}
	
	public StoreLeaguerInfo get(long id) {
		return StoreLeaguerInfoRedisDAO.getInstance().get(id);
	}

	public void delete(StoreLeaguerInfo target) {
		StoreLeaguerInfoRedisDAO.getInstance().delete(target.getId());
	}
	
}
