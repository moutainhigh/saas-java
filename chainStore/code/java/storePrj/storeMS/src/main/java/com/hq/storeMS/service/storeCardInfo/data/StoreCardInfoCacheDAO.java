package com.hq.storeMS.service.storeCardInfo.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoCacheDAO {

	public static StoreCardInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoCacheDAO.class);
	}

	public void save(StoreCardInfo target) {
		StoreCardInfoRedisDAO.getInstance().save(target);
	}
	
	public StoreCardInfo get(long id) {
		return StoreCardInfoRedisDAO.getInstance().get(id);
	}

	public void delete(StoreCardInfo target) {
		StoreCardInfoRedisDAO.getInstance().delete(target.getId());
	}
}
