package com.hq.chainStore.service.serverConfig.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestProxy;

public class ServerConfigDAO extends RestDao<ServerConfig> {

	public static ServerConfigDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ServerConfigDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public ServerConfig getServerConfigFromMS(String uriPath, ReqMap reqMap){
		final String uriPattern = "{}/{}/{}?{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "serverConfigUnAuth", uriPath, reqMap.toReqParam());
		return RestProxy.getInstance().get(uri, ServerConfig.class);
	}
	
}
