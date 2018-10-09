package com.hq.customerMS.service.storeLeaguerInfo.data;

import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoCacheDAO {

	public static StoreLeaguerInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoCacheDAO.class);
	}
	
	public StoreLeaguerInfo get(long id) {
		return StoreLeaguerInfoRedisDAO.getInstance().get(id);
	}
	
}
