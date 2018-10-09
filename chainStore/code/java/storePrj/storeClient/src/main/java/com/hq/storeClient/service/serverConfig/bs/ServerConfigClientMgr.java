package com.hq.storeClient.service.serverConfig.bs;

import com.hq.storeClient.service.serverConfig.data.ServerConfig;
import com.hq.storeClient.service.serverConfig.data.ServerConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ServerConfigClientMgr {

	public static ServerConfigClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigClientMgr.class);
	}

	public ServerConfig findOneServerConfig(String appNameCh, String appNameEn, String appVersion) {
		final String uriPath = "findOneServerConfig";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appNameCh", appNameCh).add("appNameEn", appNameEn).add("appVersion", appVersion);
		return ServerConfigDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
}
