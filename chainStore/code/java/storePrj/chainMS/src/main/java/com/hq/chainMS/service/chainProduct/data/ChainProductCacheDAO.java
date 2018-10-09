package com.hq.chainMS.service.chainProduct.data;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductCacheDAO {

	public static ChainProductCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductCacheDAO.class);
	}

	public void save(ChainProduct target) {
		ChainProductRedisDAO.getInstance().save(target);
	}
	
	public ChainProduct get(long id) {
		return ChainProductRedisDAO.getInstance().get(id);
	}

	public void delete(ChainProduct target) {
		ChainProductRedisDAO.getInstance().delete(target.getId());
	}
}
