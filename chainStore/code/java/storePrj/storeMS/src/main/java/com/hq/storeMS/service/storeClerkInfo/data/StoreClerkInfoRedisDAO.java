package com.hq.storeMS.service.storeClerkInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreClerkInfoRedisDAO extends RedisDao<StoreClerkInfo> {

	public static StoreClerkInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoRedisDAO.class);
	}

	
}
