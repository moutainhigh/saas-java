package com.hq.customerMS.service.storeCardInfo.bs;

import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoMgr {

	public static StoreCardInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoMgr.class);
	}

	public StoreCardInfo getStoreCardInfo(long storeId) {
		return StoreCardInfoDataHolder.getInstance().get(storeId);
	}
	
}
