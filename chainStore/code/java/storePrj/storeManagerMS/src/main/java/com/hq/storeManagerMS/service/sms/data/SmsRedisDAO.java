package com.hq.storeManagerMS.service.sms.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class SmsRedisDAO extends RedisDao<Sms> {
	public static SmsRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SmsRedisDAO.class);
	}
}
