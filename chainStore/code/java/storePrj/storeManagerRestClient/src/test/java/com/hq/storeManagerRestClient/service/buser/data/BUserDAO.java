package com.hq.storeManagerRestClient.service.buser.data;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BUserDAO extends RestDao<BUser> {

	public static BUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
