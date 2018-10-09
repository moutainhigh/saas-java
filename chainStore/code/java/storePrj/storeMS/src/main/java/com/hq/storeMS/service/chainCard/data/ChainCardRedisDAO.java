package com.hq.storeMS.service.chainCard.data;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainCardRedisDAO extends RedisDao<ChainCard> {

	public static ChainCardRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardRedisDAO.class);
	}
}
