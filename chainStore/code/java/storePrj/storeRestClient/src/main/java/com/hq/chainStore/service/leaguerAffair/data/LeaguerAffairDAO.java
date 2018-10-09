package com.hq.chainStore.service.leaguerAffair.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class LeaguerAffairDAO extends RestDao<LeaguerAffair> {

	public static LeaguerAffairDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
