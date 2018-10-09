package com.hq.storeMS.service.storeBeauticianInfo.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoCacheDAO {

	public static StoreBeauticianInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoCacheDAO.class);
	}

	public void save(StoreBeauticianInfo target) {
		StoreBeauticianInfoRedisDAO.getInstance().save(target);
	}
	
	public StoreBeauticianInfo get(long id) {
		return StoreBeauticianInfoRedisDAO.getInstance().get(id);
	}

	public void delete(StoreBeauticianInfo target) {
		StoreBeauticianInfoRedisDAO.getInstance().delete(target.getId());
	}
}
