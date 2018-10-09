package com.hq.storeMS.service.euser.data;

import com.zenmind.common.hotSwap.HotSwap;

public class EUserCacheDAO {

	public static EUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(EUserCacheDAO.class);
	}

	public void save(EUser target) {
		EUserRedisDAO.getInstance().save(target);
	}
	
	public EUser get(long id) {
		return EUserRedisDAO.getInstance().get(id);
	}

	public void delete(EUser target) {
		EUserRedisDAO.getInstance().delete(target.getId());
	}
}
