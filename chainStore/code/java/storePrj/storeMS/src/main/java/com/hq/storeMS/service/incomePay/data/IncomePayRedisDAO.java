package com.hq.storeMS.service.incomePay.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class IncomePayRedisDAO extends RedisDao<IncomePay> {

    public static IncomePayRedisDAO getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayRedisDAO.class);
    }
}
