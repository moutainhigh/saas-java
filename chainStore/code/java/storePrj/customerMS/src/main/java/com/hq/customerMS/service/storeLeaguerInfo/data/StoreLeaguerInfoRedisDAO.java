package com.hq.customerMS.service.storeLeaguerInfo.data;

import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreLeaguerInfoRedisDAO extends RedisDao<StoreLeaguerInfo> {

	public static StoreLeaguerInfoRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoRedisDAO.class);
	}

}
