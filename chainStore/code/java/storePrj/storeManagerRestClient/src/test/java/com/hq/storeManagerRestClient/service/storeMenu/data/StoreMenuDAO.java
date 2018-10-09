package com.hq.storeManagerRestClient.service.storeMenu.data;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreMenuDAO extends RestDao<StoreMenu> {
	public static StoreMenuDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
