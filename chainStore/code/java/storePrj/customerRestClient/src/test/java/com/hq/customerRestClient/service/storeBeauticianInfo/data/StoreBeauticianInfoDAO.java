package com.hq.customerRestClient.service.storeBeauticianInfo.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreBeauticianInfoDAO extends RestDao<StoreBeauticianInfo> {

	public static StoreBeauticianInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
