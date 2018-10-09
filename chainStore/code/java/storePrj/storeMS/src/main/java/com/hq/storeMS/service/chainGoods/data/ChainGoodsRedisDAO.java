package com.hq.storeMS.service.chainGoods.data;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainGoodsRedisDAO extends RedisDao<ChainGoods> {

	public static ChainGoodsRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsRedisDAO.class);
	}

}
