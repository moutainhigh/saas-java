package com.hq.chainStore.service.detailDataVersion.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class DetailDataVersionDAO extends RestDao<DetailDataVersion> {

	public static DetailDataVersionDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DetailDataVersionDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
