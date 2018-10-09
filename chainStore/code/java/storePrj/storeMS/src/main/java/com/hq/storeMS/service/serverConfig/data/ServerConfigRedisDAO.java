package com.hq.storeMS.service.serverConfig.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ServerConfigRedisDAO extends RedisDao<ServerConfig> {

	public static ServerConfigRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigRedisDAO.class);
	}

}
