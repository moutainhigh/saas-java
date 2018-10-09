package com.hq.chainStore.service.storeIncomePay.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreIncomePayDAO extends RestDao<StoreIncomePay> {
	public static StoreIncomePayDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreIncomePayDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
}
