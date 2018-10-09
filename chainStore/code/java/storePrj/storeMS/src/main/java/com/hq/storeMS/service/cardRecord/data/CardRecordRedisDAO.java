package com.hq.storeMS.service.cardRecord.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class CardRecordRedisDAO extends RedisDao<CardRecord> {

	public static CardRecordRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordRedisDAO.class);
	}
}
