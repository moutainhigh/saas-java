package com.hq.storeMS.service.chainProduct.data;

import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ProductDetailRedisDAO extends RedisDao<ProductDetail> {

	public static ProductDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailRedisDAO.class);
	}

}
