package com.hq.storeMS.service.serverConfig.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class ServerConfigMgr {

	public static ServerConfigMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ServerConfigMgr.class);
	}
	
	public List<ServerConfig> findServerConfigs(ServerConfigQueryForm queryForm){
		List<ServerConfig> list = ServerConfigDataHolder.getInstance().findList(queryForm);
		List<ServerConfig> serverConfigs = new ArrayList<ServerConfig>();
		if(CollectionUtils.isNotEmpty(list)){
			for (ServerConfig serverConfig : list) {
				serverConfigs.add(serverConfig);
			}
		}
		return serverConfigs;
	}
	
	//根据应用功能名称和版本号查询  版本号先用三位精确匹配，如果不存在 则用两位的版本号进行匹配
	public ServerConfig findServerConfigByOne(String appNameCh, String appNameEn, String appVersion){
		ServerConfig serverConfig = ServerConfigDataHolder.getInstance().findOne(appNameCh, appNameEn, appVersion);
		if(serverConfig == null){
			String[] appV = appVersion.split("\\.");
			appVersion = StringFormatUtil.format("{}.{}.{}", appV[0], appV[1], "0");
			serverConfig = ServerConfigDataHolder.getInstance().findOne(appNameCh, appNameEn, appVersion);
		}
		return serverConfig;
	}
	
	public ServerConfig getServerConfig(long serverConfigId){
		return ServerConfigDataHolder.getInstance().get(serverConfigId);
	}
	
	public void updServerConfig(ServerConfig target){
		ServerConfigDataHolder.getInstance().update(target);
	}
	
	public void deleteServerConfig(ServerConfig target){
		ServerConfigDataHolder.getInstance().delete(target);
	}
	
	public void addServerConfig(ServerConfig target){
		ServerConfigDataHolder.getInstance().addAndReturnId(target);
	}
	
	
}
