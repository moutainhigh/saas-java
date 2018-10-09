package com.hq.storeMS.service.leaguerAffair.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class LeaguerAffairRedisDAO extends RedisDao<LeaguerAffair> {

	public static LeaguerAffairRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairRedisDAO.class);
	}
	
}
