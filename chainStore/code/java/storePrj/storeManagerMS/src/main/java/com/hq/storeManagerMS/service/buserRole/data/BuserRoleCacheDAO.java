package com.hq.storeManagerMS.service.buserRole.data;

import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleCacheDAO {

	public static BuserRoleCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleCacheDAO.class);
	}

	public BuserRole get(long id) {
		return BuserRoleRedisDAO.getInstance().get(id);
	}
}
