package com.hq.chainMS.service.storeProductInfo.data;

import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreProductInfoRedisDAO extends RedisDao<StoreProductInfo> {

	public static StoreProductInfoRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoRedisDAO.class);
	}
}
