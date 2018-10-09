package com.hq.storeMS.service.appVersion.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class AppVersionRedisDAO extends RedisDao<AppVersion> {

	public static AppVersionRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionRedisDAO.class);
	}
}
