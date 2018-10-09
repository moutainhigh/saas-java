package com.hq.storeClient.service.chain.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainDAO extends RestDao<Chain> {

	public static ChainDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
