package com.hq.chainStore.service.storeConfig.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreConfigSynDataHolder extends AbsDataSynDataHolder<StoreConfig> {

	public static StoreConfigSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreConfig;

	protected Class<StoreConfig> getClazz() {
		return StoreConfig.class;
	}

	protected RestDao<StoreConfig> getDao() {
		return StoreConfigDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
}
