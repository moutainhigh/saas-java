package com.hq.storeMS.service.storeIncomePay.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreIncomePayRedisDAO extends RedisDao<StoreIncomePay> {
	public static StoreIncomePayRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayRedisDAO.class);
	}
}
