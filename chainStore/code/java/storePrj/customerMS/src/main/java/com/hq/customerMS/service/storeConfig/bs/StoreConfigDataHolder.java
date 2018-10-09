package com.hq.customerMS.service.storeConfig.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.storeConfig.data.StoreConfigCacheDAO;
import com.hq.storeClient.service.storeConfig.bs.StoreConfigClientMgr;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigDataHolder {

	public static StoreConfigDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigDataHolder.class);
	}

	public StoreConfig getByStoreId(long storeId) {
		StoreConfig data = StoreConfigCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreConfigClientMgr.getInstance().findStoreConfigByStoreId(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}
