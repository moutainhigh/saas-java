package com.hq.storeMS.service.productCardDetail.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ProductCardDetailRedisDAO extends RedisDao<ProductCardDetail> {

	public static ProductCardDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailRedisDAO.class);
	}

}
