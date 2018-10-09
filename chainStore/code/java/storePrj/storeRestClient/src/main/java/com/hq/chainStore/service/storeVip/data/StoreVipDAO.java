package com.hq.chainStore.service.storeVip.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreVipDAO extends RestDao<StoreVip> {

	public static StoreVipDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
