package com.hq.chainMS.service.storeLeaguerInfo.data;

import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class LeaguerDetailRedisDAO extends RedisDao<LeaguerDetail> {

	public static LeaguerDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailRedisDAO.class);
	}

}
