package com.hq.chainMS.service.chainGoods.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainGoodsRedisDAO extends RedisDao<ChainGoods> {

	public static ChainGoodsRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsRedisDAO.class);
	}

}
