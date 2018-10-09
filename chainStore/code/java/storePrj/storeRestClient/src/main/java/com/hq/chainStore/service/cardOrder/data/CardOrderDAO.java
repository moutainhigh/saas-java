package com.hq.chainStore.service.cardOrder.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class CardOrderDAO extends RestDao<CardOrder> {

	public static CardOrderDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CardOrderDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getReportService();
	}
}
