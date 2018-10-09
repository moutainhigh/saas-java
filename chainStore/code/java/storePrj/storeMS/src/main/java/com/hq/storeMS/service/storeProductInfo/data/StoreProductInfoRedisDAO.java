package com.hq.storeMS.service.storeProductInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreProductInfoRedisDAO extends RedisDao<StoreProductInfo> {

	public static StoreProductInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreProductInfoRedisDAO.class);
	}	
}
