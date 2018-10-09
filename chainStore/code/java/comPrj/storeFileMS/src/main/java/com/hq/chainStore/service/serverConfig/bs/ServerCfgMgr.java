package com.hq.chainStore.service.serverConfig.bs;

import com.hq.chainStore.service.serverConfig.apiData.CfgType;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.hq.common.config.StoreFileMSCfgMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ServerCfgMgr {

	public static ServerCfgMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ServerCfgMgr.class);
	}
	
	public ServerConfig getServerCfg(int cfgType){
		ServerConfig serverConfig = ServerConfig.newInstance();
		CfgType cfgTypeEnum = CfgType.valueOf(cfgType);
		switch (cfgTypeEnum) {
		case IMGHOST:
			serverConfig.setHost(StoreFileMSCfgMgr.getProp().getImgHost());
			break;
		default:
			break;
		}
		return serverConfig;
	}
	
	
}
