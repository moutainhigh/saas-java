package com.hq.chainStore.service.buser.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreBUserDAO extends RestDao<StoreBUser> {

	public static StoreBUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}	
}
