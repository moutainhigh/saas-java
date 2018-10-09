package com.hq.chainMS.service.detailDataVersion.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class DetailDataVersionRedisDAO extends RedisDao<DetailDataVersion> {

	public static DetailDataVersionRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DetailDataVersionRedisDAO.class);
	}

	
}
