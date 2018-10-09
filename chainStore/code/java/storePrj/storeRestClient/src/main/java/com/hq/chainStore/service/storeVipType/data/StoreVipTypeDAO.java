package com.hq.chainStore.service.storeVipType.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreVipTypeDAO extends RestDao<StoreVipType> {

	public static StoreVipTypeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipTypeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
