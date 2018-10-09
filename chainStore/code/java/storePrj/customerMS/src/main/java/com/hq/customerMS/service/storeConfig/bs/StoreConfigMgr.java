package com.hq.customerMS.service.storeConfig.bs;

import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigMgr {

	public static StoreConfigMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigMgr.class);
	}
	
	public StoreConfig getByStoreId(long storeId) {
		return StoreConfigDataHolder.getInstance().getByStoreId(storeId);
	}

}
