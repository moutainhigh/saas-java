package com.hq.customerRestClient.service.store.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreDAO extends RestDao<Store> {

	public static StoreDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
