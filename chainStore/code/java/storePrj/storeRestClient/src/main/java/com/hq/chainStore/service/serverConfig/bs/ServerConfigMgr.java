package com.hq.chainStore.service.serverConfig.bs;

import java.util.List;

import com.hq.chainStore.service.serverConfig.apiData.ServerConfigAddForm;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigUpdForm;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.hq.chainStore.service.serverConfig.data.ServerConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ServerConfigMgr {

	public static ServerConfigMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ServerConfigMgr.class);
	}
	
	//app初始化时 从后台获取参数
	public ServerConfig getServerConfigFromMS(String appNameCh, String appNameEn, String appVersion) {
		final String uriPath = "findOneServerConfig";
		ReqMap reqMap = ReqMap.newInstance().add("appNameCh", appNameCh).add("appNameEn", appNameEn).add("appVersion", appVersion);
		return ServerConfigDAO.getInstance().getServerConfigFromMS(uriPath, reqMap);
	}
	
	//========================增删改查操作 供PC端使用========================
	public List<ServerConfig> findServerConfigs(ServerConfigQueryForm queryForm) {
		final String findPath = "findServerConfigs";
		return ServerConfigDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public ServerConfig findOneServerConfig(String appNameCh, String appNameEn, String appVersion) {
		final String uriPath = "findOneServerConfig";
		ReqMap reqMap = ReqMap.newInstance().add("appNameCh", appNameCh).add("appNameEn", appNameEn).add("appVersion", appVersion);
		return ServerConfigDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
	
	public ServerConfig getServerConfig(long serverConfigId) {
		return ServerConfigDAO.getInstance().get(serverConfigId);
	}
	
	public void updServerConfig(long serverConfigId, ServerConfigUpdForm updForm) {
		ServerConfigDAO.getInstance().update(serverConfigId, updForm);
	}
	
	public ServerConfig addServerConfig(ServerConfigAddForm addForm) {
		return ServerConfigDAO.getInstance().add(addForm);
	}
}
