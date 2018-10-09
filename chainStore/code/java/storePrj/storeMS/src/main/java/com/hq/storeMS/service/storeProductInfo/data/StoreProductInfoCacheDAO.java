package com.hq.storeMS.service.storeProductInfo.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoCacheDAO {

	public static StoreProductInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoCacheDAO.class);
	}

	public void save(StoreProductInfo target) {
		StoreProductInfoRedisDAO.getInstance().save(target);
	}
	
	public StoreProductInfo get(long id) {
		return StoreProductInfoRedisDAO.getInstance().get(id);
	}

	public void delete(StoreProductInfo target) {
		StoreProductInfoRedisDAO.getInstance().delete(target.getId());
	}
}
