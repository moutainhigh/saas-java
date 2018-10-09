package com.hq.storeClient.service.storePackageProject.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StorePackageProjectDAO extends RestDao<StorePackageProject> {
	public static StorePackageProjectDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
