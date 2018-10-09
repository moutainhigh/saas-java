package com.hq.customerMS.service.storeConfig.data;

import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreConfigRedisDAO extends RedisDao<StoreConfig> {

	public static StoreConfigRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigRedisDAO.class);
	}

}
