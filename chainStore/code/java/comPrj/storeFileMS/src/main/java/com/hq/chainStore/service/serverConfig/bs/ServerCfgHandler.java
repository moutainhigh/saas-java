package com.hq.chainStore.service.serverConfig.bs;

import com.hq.chainStore.service.common.ReqResult;
import com.hq.chainStore.service.common.RespStatus;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class ServerCfgHandler {

	public static ServerCfgHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ServerCfgHandler.class);
	}

	public ReqResult<ServerConfig> getServerCfg(int cfgType) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			ServerConfig serverConfig = ServerCfgMgr.getInstance().getServerCfg(cfgType);
			result.setTarget(serverConfig);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "ServerCfgHandler[getServerCfg]", "", e);
		}
		return result;
	}

}
