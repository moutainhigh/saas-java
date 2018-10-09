package com.hq.storeClient.service.appVersion.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class AppVersionDAO extends RestDao<AppVersion> {

	public static AppVersionDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppVersionDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
