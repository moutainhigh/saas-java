package com.hq.chainMS.service.chainPackageProject.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ChainPackageProjectRedisDAO extends RedisDao<ChainPackageProject> {

	public static ChainPackageProjectRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectRedisDAO.class);
	}
	
}
