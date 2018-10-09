package com.hq.storeMS.service.storeVipType.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreVipTypeRedisDAO extends RedisDao<StoreVipType> {

	public static StoreVipTypeRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreVipTypeRedisDAO.class);
	}

}
