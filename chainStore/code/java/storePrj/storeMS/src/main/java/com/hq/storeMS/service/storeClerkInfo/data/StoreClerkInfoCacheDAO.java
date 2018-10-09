package com.hq.storeMS.service.storeClerkInfo.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoCacheDAO {

	public static StoreClerkInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoCacheDAO.class);
	}

	public void save(StoreClerkInfo target) {
		StoreClerkInfoRedisDAO.getInstance().save(target);
	}
	
	public StoreClerkInfo get(String id) {
		return StoreClerkInfoRedisDAO.getInstance().get(id);
	}

	public void delete(StoreClerkInfo target) {
		StoreClerkInfoRedisDAO.getInstance().delete(target.getId());
	}
}
