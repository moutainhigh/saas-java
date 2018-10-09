package com.hq.chainMS.service.storeClerk.data;

import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreClerkInfoRedisDAO extends RedisDao<StoreClerkInfo> {

	public static StoreClerkInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoRedisDAO.class);
	}
	
}
