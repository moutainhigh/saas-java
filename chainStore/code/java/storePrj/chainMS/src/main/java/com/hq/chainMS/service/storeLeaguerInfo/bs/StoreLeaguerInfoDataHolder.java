package com.hq.chainMS.service.storeLeaguerInfo.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeLeaguerInfo.data.StoreLeaguerInfoCacheDAO;
import com.hq.storeClient.service.storeLeaguerInfo.bs.StoreLeaguerInfoClientMgr;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoDataHolder {

	public static StoreLeaguerInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDataHolder.class);
	}

	public StoreLeaguerInfo getStoreLeaguerInfo(long storeId) {
		StoreLeaguerInfo data = StoreLeaguerInfoCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
}
