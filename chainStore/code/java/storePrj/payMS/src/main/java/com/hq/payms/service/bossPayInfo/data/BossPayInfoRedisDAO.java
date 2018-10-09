package com.hq.payms.service.bossPayInfo.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BossPayInfoRedisDAO extends RedisDao<BossPayInfo> {

	public static BossPayInfoRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoRedisDAO.class);
	}

	
}
