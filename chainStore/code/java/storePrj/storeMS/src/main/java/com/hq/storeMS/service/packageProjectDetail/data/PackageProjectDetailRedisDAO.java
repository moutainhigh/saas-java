package com.hq.storeMS.service.packageProjectDetail.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class PackageProjectDetailRedisDAO extends RedisDao<PackageProjectDetail> {

	public static PackageProjectDetailRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailRedisDAO.class);
	}

}
