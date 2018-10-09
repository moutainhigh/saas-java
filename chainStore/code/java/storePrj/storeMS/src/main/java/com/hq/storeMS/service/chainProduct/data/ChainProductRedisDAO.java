package com.hq.storeMS.service.chainProduct.data;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainProductRedisDAO extends RedisDao<ChainProduct> {

	public static ChainProductRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductRedisDAO.class);
	}

}
