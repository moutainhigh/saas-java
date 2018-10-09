package com.hq.storeMS.service.storeLeaguerInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreLeaguerInfoRedisDAO extends RedisDao<StoreLeaguerInfo> {

	public static StoreLeaguerInfoRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoRedisDAO.class);
	}
}
