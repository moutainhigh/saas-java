package com.hq.chainStore.service.storePackageProject.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StorePackageProjectDAO extends RestDao<StorePackageProject> {

	public static StorePackageProjectDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StorePackageProjectDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
}
