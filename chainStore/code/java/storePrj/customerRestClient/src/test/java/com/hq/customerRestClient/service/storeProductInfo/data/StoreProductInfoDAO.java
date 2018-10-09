package com.hq.customerRestClient.service.storeProductInfo.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreProductInfoDAO extends RestDao<StoreProductInfo> {
	public static StoreProductInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
