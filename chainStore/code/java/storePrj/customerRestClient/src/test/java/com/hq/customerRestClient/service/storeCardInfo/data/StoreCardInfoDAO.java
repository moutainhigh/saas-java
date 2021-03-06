package com.hq.customerRestClient.service.storeCardInfo.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreCardInfoDAO extends RestDao<StoreCardInfo> {
	public static StoreCardInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
