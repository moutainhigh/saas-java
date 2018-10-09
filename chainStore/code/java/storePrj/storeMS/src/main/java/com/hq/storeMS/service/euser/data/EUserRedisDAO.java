package com.hq.storeMS.service.euser.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class EUserRedisDAO extends RedisDao<EUser> {

	public static EUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(EUserRedisDAO.class);
	}
	
}
