package com.hq.customerRestClient.service.cuser.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class CUserDAO extends RestDao<CUser> {

	public static CUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
