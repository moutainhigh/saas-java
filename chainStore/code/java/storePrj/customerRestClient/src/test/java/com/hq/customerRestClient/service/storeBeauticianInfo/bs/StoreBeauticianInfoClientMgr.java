package com.hq.customerRestClient.service.storeBeauticianInfo.bs;

import com.hq.customerRestClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.customerRestClient.service.storeBeauticianInfo.data.StoreBeauticianInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoClientMgr {

	public static StoreBeauticianInfoClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoClientMgr.class);
	}

	public StoreBeauticianInfo get(long storeId) {
		return StoreBeauticianInfoDAO.getInstance().get(storeId);
	}
	
}
