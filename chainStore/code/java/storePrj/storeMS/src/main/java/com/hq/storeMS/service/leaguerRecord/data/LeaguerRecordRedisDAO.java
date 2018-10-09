package com.hq.storeMS.service.leaguerRecord.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class LeaguerRecordRedisDAO extends RedisDao<LeaguerRecord> {

	public static LeaguerRecordRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerRecordRedisDAO.class);
	}
}
