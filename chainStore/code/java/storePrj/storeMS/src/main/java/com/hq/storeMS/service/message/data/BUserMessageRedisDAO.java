package com.hq.storeMS.service.message.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BUserMessageRedisDAO extends RedisDao<BUserMessage> {

	public static BUserMessageRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageRedisDAO.class);
	}
}
