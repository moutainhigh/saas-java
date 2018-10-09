package com.hq.chainMS.service.chainUser.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainUserRedisDAO extends RedisDao<ChainUser> {

	public static ChainUserRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserRedisDAO.class);
	}
}
