package com.hq.storeClient.service.storeClerkInfo.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreClerkInfoDAO extends RestDao<StoreClerkInfo> {

	public static StoreClerkInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
