package com.hq.customerRestClient.service.storeCardInfo.bs;

import com.hq.customerRestClient.service.storeCardInfo.data.StoreCardInfo;
import com.hq.customerRestClient.service.storeCardInfo.data.StoreCardInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoClientMgr {

	public static StoreCardInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoClientMgr.class);
	}

	public StoreCardInfo get(long storeId) {
		return StoreCardInfoDAO.getInstance().get(storeId);
	}
	
}
