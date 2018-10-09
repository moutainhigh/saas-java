package com.hq.customerMS.service.storeProductInfo.data;

import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoCacheDAO {

	public static StoreProductInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoCacheDAO.class);
	}
	
	public StoreProductInfo get(long id) {
		return StoreProductInfoRedisDAO.getInstance().get(id);
	}
}
