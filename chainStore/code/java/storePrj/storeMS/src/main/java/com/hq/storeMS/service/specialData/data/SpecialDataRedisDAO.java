package com.hq.storeMS.service.specialData.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class SpecialDataRedisDAO extends RedisDao<SpecialData> {

	public static SpecialDataRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataRedisDAO.class);
	}

}
