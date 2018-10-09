package com.hq.chainMS.service.chainProduct.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainProductRedisDAO extends RedisDao<ChainProduct> {

	public static ChainProductRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductRedisDAO.class);
	}

}
