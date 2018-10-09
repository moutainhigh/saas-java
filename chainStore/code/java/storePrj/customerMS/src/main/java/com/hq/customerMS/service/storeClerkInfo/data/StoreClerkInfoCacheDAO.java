package com.hq.customerMS.service.storeClerkInfo.data;

import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoCacheDAO {

	public static StoreClerkInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoCacheDAO.class);
	}
	
	public StoreClerkInfo get(String id) {
		return StoreClerkInfoRedisDAO.getInstance().get(id);
	}
}
