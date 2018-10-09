package com.hq.customerMS.service.storeCardInfo.data;

import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoCacheDAO {

	public static StoreCardInfoCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoCacheDAO.class);
	}
	
	public StoreCardInfo get(long id) {
		return StoreCardInfoRedisDAO.getInstance().get(id);
	}
}
