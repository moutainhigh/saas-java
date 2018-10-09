package com.hq.storeMS.service.goodsDetail.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class GoodsDetailRedisDAO extends RedisDao<GoodsDetail> {

	public static GoodsDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailRedisDAO.class);
	}
}
