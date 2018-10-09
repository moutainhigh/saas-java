package com.hq.chainStore.service.serverConfig.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ServerConfigSynDataHolder extends AbsDataSynDataHolder<ServerConfig> {

	public static ServerConfigSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ServerConfig;

	protected Class<ServerConfig> getClazz() {
		return ServerConfig.class;
	}

	protected RestDao<ServerConfig> getDao() {
		return ServerConfigDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
