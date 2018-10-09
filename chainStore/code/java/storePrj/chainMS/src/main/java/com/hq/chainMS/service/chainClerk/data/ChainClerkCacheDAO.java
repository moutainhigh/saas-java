package com.hq.chainMS.service.chainClerk.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainClerkCacheDAO {

	public static ChainClerkCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkCacheDAO.class);
	}

	public void save(ChainClerk target) {
		ChainClerkRedisDAO.getInstance().save(target);
	}
	
	public ChainClerk get(Object id) {
		return ChainClerkRedisDAO.getInstance().get(id);
	}

	public void delete(ChainClerk target) {
		ChainClerkRedisDAO.getInstance().delete(target.getId());
	}
	
}
