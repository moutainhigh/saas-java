package com.hq.customerRestClient.service.leaguerDetail.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class LeaguerDetailDAO extends RestDao<LeaguerDetail> {

	public static LeaguerDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
