package com.hq.storeMS.service.opuser.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OPUserRedisDAO extends RedisDao<OPUser> {

	public static OPUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserRedisDAO.class);
	}
	
}
