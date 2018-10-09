package com.hq.chainMS.service.chainCard.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardCacheDAO {

	public static ChainCardCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardCacheDAO.class);
	}

	public void save(ChainCard target) {
		ChainCardRedisDAO.getInstance().save(target);
	}
	
	public ChainCard get(long id) {
		return ChainCardRedisDAO.getInstance().get(id);
	}

	public void delete(ChainCard target) {
		ChainCardRedisDAO.getInstance().delete(target.getId());
	}
	
}
