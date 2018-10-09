package com.hq.storeMS.service.footprint.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class FootprintRedisDAO extends RedisDao<Footprint> {

	public static FootprintRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(FootprintRedisDAO.class);
	}
}
