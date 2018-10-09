package com.hq.chainStore.service.beauticianProduct.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BeauticianProductDAO extends RestDao<BeauticianProduct> {

	public static BeauticianProductDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
