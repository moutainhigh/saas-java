package com.hq.storeManagerMS.service.buserRole.data;

import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BuserRoleRedisDAO  extends RedisDao<BuserRole>{

	public static BuserRoleRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleRedisDAO.class);
	}
}
