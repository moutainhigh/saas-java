package com.hq.storeManagerMS.service.storeMenu.bs;

import com.hq.storeClient.service.storeMenu.bs.StoreMenuClientMgr;
import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.validate.AppIdThreadLocal;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuDataHolder {
	
	public static StoreMenuDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMenuDataHolder.class);
	}
	
	public StoreMenu get() {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreMenu data = StoreMenuClientMgr.getInstance().getStoreMenu();
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
