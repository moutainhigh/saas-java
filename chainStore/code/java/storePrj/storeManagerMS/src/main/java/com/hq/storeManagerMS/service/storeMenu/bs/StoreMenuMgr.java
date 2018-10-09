package com.hq.storeManagerMS.service.storeMenu.bs;

import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuMgr {

	public static StoreMenuMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuMgr.class);
	}
	
	public StoreMenu get() {
		return StoreMenuDataHolder.getInstance().get();
	}
}
