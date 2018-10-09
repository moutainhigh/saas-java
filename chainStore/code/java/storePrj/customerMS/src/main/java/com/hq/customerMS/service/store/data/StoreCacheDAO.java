package com.hq.customerMS.service.store.data;

import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCacheDAO {

	public static StoreCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCacheDAO.class);
	}

	public Store get(long id) {
		return StoreRedisDAO.getInstance().get(id);
	}
}
