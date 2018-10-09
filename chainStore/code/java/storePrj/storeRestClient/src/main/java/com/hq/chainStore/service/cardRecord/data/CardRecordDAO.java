package com.hq.chainStore.service.cardRecord.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class CardRecordDAO extends RestDao<CardRecord> {

	public static CardRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
}
