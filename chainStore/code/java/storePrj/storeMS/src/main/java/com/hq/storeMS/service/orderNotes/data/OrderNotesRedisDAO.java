package com.hq.storeMS.service.orderNotes.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class OrderNotesRedisDAO extends RedisDao<OrderNotes> {
    public static OrderNotesRedisDAO getInstance() {
        return HotSwap.getInstance().getSingleton(OrderNotesRedisDAO.class);
    }
}
