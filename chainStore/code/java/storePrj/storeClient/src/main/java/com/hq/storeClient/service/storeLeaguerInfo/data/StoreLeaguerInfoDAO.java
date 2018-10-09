package com.hq.storeClient.service.storeLeaguerInfo.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreLeaguerInfoDAO extends RestDao<StoreLeaguerInfo> {
	public static StoreLeaguerInfoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
