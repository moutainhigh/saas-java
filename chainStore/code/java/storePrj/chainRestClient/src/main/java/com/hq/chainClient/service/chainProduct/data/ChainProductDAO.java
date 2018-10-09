package com.hq.chainClient.service.chainProduct.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainProductDAO extends RestDao<ChainProduct> {

	public static ChainProductDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
