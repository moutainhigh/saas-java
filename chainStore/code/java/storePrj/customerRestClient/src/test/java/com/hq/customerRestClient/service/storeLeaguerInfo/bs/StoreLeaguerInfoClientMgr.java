package com.hq.customerRestClient.service.storeLeaguerInfo.bs;

import com.hq.customerRestClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.customerRestClient.service.storeLeaguerInfo.data.StoreLeaguerInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoClientMgr {

	public static StoreLeaguerInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoClientMgr.class);
	}

	public StoreLeaguerInfo get(long storeId) {
		return StoreLeaguerInfoDAO.getInstance().get(storeId);
	}
	
}
