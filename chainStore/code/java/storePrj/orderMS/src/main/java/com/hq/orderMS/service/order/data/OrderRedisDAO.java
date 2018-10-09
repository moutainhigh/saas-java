package com.hq.orderMS.service.order.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OrderRedisDAO extends RedisDao<Order> {
	public static OrderRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderRedisDAO.class);
	}
}
