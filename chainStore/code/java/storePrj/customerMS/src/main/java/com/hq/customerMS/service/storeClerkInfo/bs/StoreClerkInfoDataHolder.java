package com.hq.customerMS.service.storeClerkInfo.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.storeClerkInfo.data.StoreClerkInfoCacheDAO;
import com.hq.storeClient.service.storeClerkInfo.bs.StoreClerkInfoClientMgr;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoDataHolder {
	
	public static StoreClerkInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoDataHolder.class);
	}
	
	public StoreClerkInfo get(String id) {
		StoreClerkInfo data = StoreClerkInfoCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreClerkInfoClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
