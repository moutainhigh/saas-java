package com.hq.storeMS.service.buser.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BUserRedisDAO extends RedisDao<BUser> {

	public static BUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserRedisDAO.class);
	}
	
}
