package com.hq.storeManagerMS.service.storeMenu.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuCacheDAO {

	public static StoreMenuCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuCacheDAO.class);
	}
}
