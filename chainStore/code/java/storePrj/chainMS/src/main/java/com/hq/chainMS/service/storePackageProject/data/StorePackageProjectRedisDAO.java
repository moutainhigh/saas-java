package com.hq.chainMS.service.storePackageProject.data;

import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class StorePackageProjectRedisDAO extends RedisDao<StorePackageProject> {

	public static StorePackageProjectRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectRedisDAO.class);
	}
}
