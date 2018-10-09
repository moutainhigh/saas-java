package com.hq.storeMS.service.serverConfig.data;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestProxy;

@Deprecated
public class  ServerCfgDAO {
	
	public static ServerCfgDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ServerCfgDAO.class);
	}
	
	public ServerConfig getImgHost(ReqMap reqMap){
		final String uriPattern = "{}/serverCfg/getServerCfg?{}";
		String uri = StringFormatUtil.format(uriPattern, StoreMSCfgMgr.getProp().getFileHost(), reqMap.toReqParam());
		return RestProxy.getInstance().get(uri, ServerConfig.class);
	}
}
