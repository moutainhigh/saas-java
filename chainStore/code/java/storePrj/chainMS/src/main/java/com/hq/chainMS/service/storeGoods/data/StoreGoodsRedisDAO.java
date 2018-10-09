package com.hq.chainMS.service.storeGoods.data;

import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StoreGoodsRedisDAO extends RedisDao<StoreGoods> {

	public static StoreGoodsRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsRedisDAO.class);
	}

}
