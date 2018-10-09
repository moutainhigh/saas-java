package com.hq.storeClient.service.storeLeaguerInfo.bs;

import com.hq.storeClient.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoClientMgr {

	public static StoreLeaguerInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoClientMgr.class);
	}

	public StoreLeaguerInfo get(long storeId) {
		return StoreLeaguerInfoDAO.getInstance().get(storeId);
	}
	
	public void updateStoreLeaguerInfo(long storeId, StoreLeaguerInfoUpdateApiForm updateForm) {
		StoreLeaguerInfoDAO.getInstance().update(storeId, updateForm);
	}
}
