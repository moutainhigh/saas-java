package com.hq.chainMS.service.areaCode.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class AreaCodeRedisDAO extends RedisDao<AreaCode> {

	public static AreaCodeRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeRedisDAO.class);
	}
	
}
