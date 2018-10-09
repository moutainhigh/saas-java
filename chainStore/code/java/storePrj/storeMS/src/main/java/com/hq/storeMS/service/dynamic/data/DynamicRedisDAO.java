package com.hq.storeMS.service.dynamic.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class DynamicRedisDAO extends RedisDao<Dynamic> {

	public static DynamicRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DynamicRedisDAO.class);
	}
}
