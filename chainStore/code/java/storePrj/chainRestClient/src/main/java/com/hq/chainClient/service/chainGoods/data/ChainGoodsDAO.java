package com.hq.chainClient.service.chainGoods.data;

import com.hq.chainClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainGoodsDAO extends RestDao<ChainGoods> {

	public static ChainGoodsDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainGoodsDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
