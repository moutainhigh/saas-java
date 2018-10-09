package com.hq.customerMS.service.storeBeauticianInfo.data;

import com.hq.storeClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoCacheDAO {

	public static StoreBeauticianInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoCacheDAO.class);
	}

	public StoreBeauticianInfo get(long id) {
		return StoreBeauticianInfoRedisDAO.getInstance().get(id);
	}
}
