package com.hq.storeMS.service.buserRole.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BuserRoleRedisDAO extends RedisDao<BuserRole> {

	public static BuserRoleRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleRedisDAO.class);
	}
}
