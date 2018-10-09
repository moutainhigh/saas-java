package com.hq.storeMS.service.opuser.data;

import com.zenmind.common.hotSwap.HotSwap;

public class OPUserCacheDAO {

	public static OPUserCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OPUserCacheDAO.class);
	}

	public void save(OPUser target) {
		OPUserRedisDAO.getInstance().save(target);
	}
	
	public OPUser get(long id) {
		return OPUserRedisDAO.getInstance().get(id);
	}

	public void delete(OPUser target) {
		OPUserRedisDAO.getInstance().delete(target.getId());
	}
}
