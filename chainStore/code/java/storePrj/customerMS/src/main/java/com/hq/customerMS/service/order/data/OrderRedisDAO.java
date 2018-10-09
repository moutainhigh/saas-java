package com.hq.customerMS.service.order.data;

import com.hq.storeClient.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OrderRedisDAO extends RedisDao<Order> {

	public static OrderRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderRedisDAO.class);
	}
}
