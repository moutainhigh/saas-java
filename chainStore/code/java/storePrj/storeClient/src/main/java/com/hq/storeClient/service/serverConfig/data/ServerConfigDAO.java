package com.hq.storeClient.service.serverConfig.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ServerConfigDAO extends RestDao<ServerConfig> {

	public static ServerConfigDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
