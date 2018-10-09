package com.hq.storeMS.service.cuserChainData.data;

import com.zenmind.common.hotSwap.HotSwap;

public class CuserChainDataCacheDAO {

	public static CuserChainDataCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataCacheDAO.class);
	}

	public void save(CuserChainData target) {
		CuserChainDataRedisDAO.getInstance().save(target);
	}
	
	public CuserChainData get(long id) {
		return CuserChainDataRedisDAO.getInstance().get(id);
	}

	public void delete(CuserChainData target) {
		CuserChainDataRedisDAO.getInstance().delete(target.getId());
	}
}
