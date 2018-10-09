package com.hq.storeManagerMS.service.charge.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChargeRedisDAO  extends RedisDao<Charge>{

	public static ChargeRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeRedisDAO.class);
	}
}
