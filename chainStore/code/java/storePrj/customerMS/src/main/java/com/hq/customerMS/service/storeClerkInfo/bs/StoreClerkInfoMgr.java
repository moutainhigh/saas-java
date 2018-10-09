package com.hq.customerMS.service.storeClerkInfo.bs;

import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoMgr {

	public static StoreClerkInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoMgr.class);
	}
	
	public StoreClerkInfo get(String id) {
		return StoreClerkInfoDataHolder.getInstance().get(id);
	}
}
