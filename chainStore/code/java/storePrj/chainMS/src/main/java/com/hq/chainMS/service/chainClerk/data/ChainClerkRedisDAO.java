package com.hq.chainMS.service.chainClerk.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainClerkRedisDAO extends RedisDao<ChainClerk> {

	public static ChainClerkRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkRedisDAO.class);
	}

}
