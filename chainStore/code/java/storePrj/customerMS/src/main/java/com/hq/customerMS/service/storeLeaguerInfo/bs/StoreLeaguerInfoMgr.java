package com.hq.customerMS.service.storeLeaguerInfo.bs;

import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoMgr {

	public static StoreLeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoMgr.class);
	}

	public StoreLeaguerInfo getByStoreId(long storeId) {
		return StoreLeaguerInfoDataHolder.getInstance().get(storeId);
	}

}
