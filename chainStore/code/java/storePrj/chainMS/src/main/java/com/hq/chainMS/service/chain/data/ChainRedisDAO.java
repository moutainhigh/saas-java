package com.hq.chainMS.service.chain.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainRedisDAO extends RedisDao<Chain> {

	public static ChainRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainRedisDAO.class);
	}	
}
