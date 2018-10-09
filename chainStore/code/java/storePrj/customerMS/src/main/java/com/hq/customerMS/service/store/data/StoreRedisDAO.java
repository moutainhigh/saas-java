package com.hq.customerMS.service.store.data;

import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreRedisDAO extends RedisDao<Store> {

	public static StoreRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreRedisDAO.class);
	}

}
