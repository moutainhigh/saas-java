package com.hq.storeClient.service.storeMenu.bs;

import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.hq.storeClient.service.storeMenu.data.StoreMenuDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuClientMgr {

	public static StoreMenuClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuClientMgr.class);
	}

	public StoreMenu getStoreMenu() {
		String uriPath = "getStoreMenu";
		return StoreMenuDAO.getInstance().findOne(uriPath);
	}

}
