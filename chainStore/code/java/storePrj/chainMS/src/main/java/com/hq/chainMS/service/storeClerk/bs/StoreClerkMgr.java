package com.hq.chainMS.service.storeClerk.bs;

import com.hq.storeClient.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkMgr {

	public static StoreClerkMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkMgr.class);
	}
	
	public StoreClerkInfo get(long storeId) {
		return StoreClerkDataHolder.getInstance().getStoreClerk(storeId);
	}
	
	public void applyClerkInfo(long storeId, ApplyClerkInfoData applyClerkInfoData) {
		StoreClerkDataHolder.getInstance().applyClerkInfo(storeId, applyClerkInfoData);
	}
}
