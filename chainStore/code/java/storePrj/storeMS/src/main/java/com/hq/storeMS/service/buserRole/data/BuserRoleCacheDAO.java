package com.hq.storeMS.service.buserRole.data;

import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleCacheDAO {

	public static BuserRoleCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleCacheDAO.class);
	}

	public void save(BuserRole target) {
		BuserRoleRedisDAO.getInstance().save(target);
	}
	
	public BuserRole get(long id) {
		return BuserRoleRedisDAO.getInstance().get(id);
	}

	public void delete(BuserRole target) {
		BuserRoleRedisDAO.getInstance().delete(target.getId());
	}
}
