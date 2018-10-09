package com.hq.chainStore.service.storeMaterialInfo.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreMaterialInfoDAO extends RestDao<StoreMaterialInfo> {

	public static StoreMaterialInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
