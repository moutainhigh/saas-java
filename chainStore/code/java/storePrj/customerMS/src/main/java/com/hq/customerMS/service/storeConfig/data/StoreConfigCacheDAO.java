package com.hq.customerMS.service.storeConfig.data;

import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigCacheDAO {

	public static StoreConfigCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigCacheDAO.class);
	}
	
	public StoreConfig get(long id) {
		return StoreConfigRedisDAO.getInstance().get(id);
	}
}
