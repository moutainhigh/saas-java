package com.hq.storeMS.service.orderTrack.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OrderTrackRedisDAO extends RedisDao<OrderTrack> {
    public static OrderTrackRedisDAO getInstance() {
        return HotSwap.getInstance().getSingleton(OrderTrackRedisDAO.class);
    }
}
