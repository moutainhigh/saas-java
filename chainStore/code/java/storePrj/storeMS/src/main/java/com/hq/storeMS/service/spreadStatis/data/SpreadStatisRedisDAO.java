package com.hq.storeMS.service.spreadStatis.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class SpreadStatisRedisDAO extends RedisDao<SpreadStatis> {

    public static SpreadStatisRedisDAO getInstance() {
        return HotSwap.getInstance().getSingleton(SpreadStatisRedisDAO.class);
    }
}
