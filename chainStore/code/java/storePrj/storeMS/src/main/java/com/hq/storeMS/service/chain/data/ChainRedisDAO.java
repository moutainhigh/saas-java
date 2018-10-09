package com.hq.storeMS.service.chain.data;

import com.hq.chainClient.service.chain.data.Chain;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainRedisDAO extends RedisDao<Chain> {

	public static ChainRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainRedisDAO.class);
	}	
}
