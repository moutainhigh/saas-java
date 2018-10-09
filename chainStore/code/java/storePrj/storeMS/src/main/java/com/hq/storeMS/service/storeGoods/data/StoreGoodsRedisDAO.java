package com.hq.storeMS.service.storeGoods.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreGoodsRedisDAO extends RedisDao<StoreGoods> {

	public static StoreGoodsRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsRedisDAO.class);
	}

}
