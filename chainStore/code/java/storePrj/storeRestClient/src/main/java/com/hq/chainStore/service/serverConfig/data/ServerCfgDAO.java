package com.hq.chainStore.service.serverConfig.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

/**
 * 
 * @Deprecated 已经被ServerConfigDAO替代
 *
 */
@Deprecated
public class ServerCfgDAO extends RestDao<ServerConfig> {

	public static ServerCfgDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ServerCfgDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

}
