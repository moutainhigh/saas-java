package com.hq.chainClient.service.chainCard.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainCardDAO extends RestDao<ChainCard> {

	public static ChainCardDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainCardDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
