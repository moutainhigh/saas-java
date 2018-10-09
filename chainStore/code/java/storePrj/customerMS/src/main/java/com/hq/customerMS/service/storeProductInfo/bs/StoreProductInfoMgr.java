package com.hq.customerMS.service.storeProductInfo.bs;

import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoMgr {

	public static StoreProductInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoMgr.class);
	}

	public StoreProductInfo get(long id) {
		return StoreProductInfoDataHolder.getInstance().get(id);
	}

}
