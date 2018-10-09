package com.hq.storeMS.service.detailDataVersion.data;

import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionCacheDAO {

	public static DetailDataVersionCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionCacheDAO.class);
	}

	public void save(DetailDataVersion target) {
		DetailDataVersionRedisDAO.getInstance().save(target);
	}
	
	public DetailDataVersion get(long id) {
		return DetailDataVersionRedisDAO.getInstance().get(id);
	}

	public void delete(DetailDataVersion target) {
		DetailDataVersionRedisDAO.getInstance().delete(target.getId());
	}
}
