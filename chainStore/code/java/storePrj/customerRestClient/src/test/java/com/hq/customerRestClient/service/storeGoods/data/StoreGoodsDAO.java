package com.hq.customerRestClient.service.storeGoods.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreGoodsDAO extends RestDao<StoreGoods> {
	public static StoreGoodsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
