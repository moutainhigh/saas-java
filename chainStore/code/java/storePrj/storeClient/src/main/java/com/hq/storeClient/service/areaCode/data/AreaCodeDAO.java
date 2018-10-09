package com.hq.storeClient.service.areaCode.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class AreaCodeDAO extends RestDao<AreaCode> {

	public static AreaCodeDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
