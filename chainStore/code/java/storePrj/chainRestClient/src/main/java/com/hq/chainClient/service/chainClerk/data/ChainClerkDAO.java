package com.hq.chainClient.service.chainClerk.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainClerkDAO extends RestDao<ChainClerk> {

	public static ChainClerkDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainClerkDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
