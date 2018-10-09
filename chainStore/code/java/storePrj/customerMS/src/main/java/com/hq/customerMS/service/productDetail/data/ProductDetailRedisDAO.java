package com.hq.customerMS.service.productDetail.data;

import com.hq.storeClient.service.productDetail.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ProductDetailRedisDAO extends RedisDao<ProductDetail> {

	public static ProductDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailRedisDAO.class);
	}

}
