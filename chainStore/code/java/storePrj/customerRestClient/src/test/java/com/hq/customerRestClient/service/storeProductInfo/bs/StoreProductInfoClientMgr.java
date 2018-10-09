package com.hq.customerRestClient.service.storeProductInfo.bs;

import com.hq.customerRestClient.service.storeProductInfo.data.StoreProductInfo;
import com.hq.customerRestClient.service.storeProductInfo.data.StoreProductInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoClientMgr {

	public static StoreProductInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoClientMgr.class);
	}
	
	public StoreProductInfo get(long storeId){
		return StoreProductInfoDAO.getInstance().get(storeId);
	}
	
}
