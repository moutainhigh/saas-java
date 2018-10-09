package com.hq.chainMS.service.chainCard.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainCardRedisDAO extends RedisDao<ChainCard> {

	public static ChainCardRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardRedisDAO.class);
	}
}
