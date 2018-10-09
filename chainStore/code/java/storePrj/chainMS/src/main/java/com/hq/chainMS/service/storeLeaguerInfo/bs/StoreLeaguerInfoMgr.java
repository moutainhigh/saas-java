package com.hq.chainMS.service.storeLeaguerInfo.bs;

import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoMgr {

	public static StoreLeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoMgr.class);
	}
	
	public StoreLeaguerInfo getStoreLeaguerInfo(long storeId) {
		return StoreLeaguerInfoDataHolder.getInstance().getStoreLeaguerInfo(storeId);
	}
}
