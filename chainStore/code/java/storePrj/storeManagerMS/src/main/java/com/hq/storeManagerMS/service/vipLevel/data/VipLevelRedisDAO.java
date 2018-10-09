package com.hq.storeManagerMS.service.vipLevel.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class VipLevelRedisDAO extends RedisDao<VipLevel> {

	public static VipLevelRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelRedisDAO.class);
	}

}
