package com.hq.chainStore.service.saas.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OPStoreDAO extends RestDao<OPStore> {

	public static OPStoreDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OPStoreDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	
}
