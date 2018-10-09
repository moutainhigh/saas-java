package com.hq.chainStore.service.storeConfig.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreConfigDAO extends RestDao<StoreConfig> {
	public static StoreConfigDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
}
