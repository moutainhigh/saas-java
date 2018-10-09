package com.hq.storeMS.service.opLog.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OpLogRedisDAO extends RedisDao<OpLog> {
	public static OpLogRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OpLogRedisDAO.class);
	}
}
