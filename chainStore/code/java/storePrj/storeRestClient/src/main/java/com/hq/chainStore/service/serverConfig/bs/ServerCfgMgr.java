package com.hq.chainStore.service.serverConfig.bs;

import com.hq.chainStore.service.serverConfig.data.ServerCfgDAO;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

/**
 * 
 * @Deprecated 已经被ServerConfigMgr替代
 *
 */
@Deprecated
public class ServerCfgMgr {

	public static ServerCfgMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ServerCfgMgr.class);
	}
	
	public ServerConfig getServerCfg(int cfgType) {

		final String uriPath = "getServerCfg";
		ReqMap reqMap = ReqMap.newInstance().add("cfgType", cfgType);
		ServerConfig serverConfig = ServerCfgDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
		return serverConfig;
	}
	
	
}
