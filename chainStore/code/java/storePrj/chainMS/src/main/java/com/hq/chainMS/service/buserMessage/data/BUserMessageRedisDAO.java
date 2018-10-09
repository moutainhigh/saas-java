package com.hq.chainMS.service.buserMessage.data;

import com.hq.storeClient.service.message.data.BUserMessage;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BUserMessageRedisDAO extends RedisDao<BUserMessage> {

	public static BUserMessageRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMessageRedisDAO.class);
	}
	
}
