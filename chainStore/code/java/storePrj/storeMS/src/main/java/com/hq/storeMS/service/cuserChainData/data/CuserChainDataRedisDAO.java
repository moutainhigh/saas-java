package com.hq.storeMS.service.cuserChainData.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class CuserChainDataRedisDAO extends RedisDao<CuserChainData> {

	public static CuserChainDataRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataRedisDAO.class);
	}
}
