package com.hq.storeClient.service.storeChain.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreChainDAO extends RestDao<StoreChain> {

	public static StoreChainDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreChainDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
