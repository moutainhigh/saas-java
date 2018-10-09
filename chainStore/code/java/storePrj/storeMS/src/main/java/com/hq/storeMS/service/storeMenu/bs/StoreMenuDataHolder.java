package com.hq.storeMS.service.storeMenu.bs;

import com.hq.storeMS.service.storeMenu.data.StoreMenu;
import com.hq.storeMS.service.storeMenu.data.StoreMenuBuilder;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuDataHolder {
	
	public static StoreMenuDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMenuDataHolder.class);
	}
	
	public StoreMenu get() {
		return StoreMenuBuilder.getInstance().build();
	}
}
