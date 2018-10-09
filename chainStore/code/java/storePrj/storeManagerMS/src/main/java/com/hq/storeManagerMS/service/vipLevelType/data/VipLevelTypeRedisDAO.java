package com.hq.storeManagerMS.service.vipLevelType.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class VipLevelTypeRedisDAO extends RedisDao<VipLevelType> {

	public static VipLevelTypeRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeRedisDAO.class);
	}

}
