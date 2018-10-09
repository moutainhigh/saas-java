package com.hq.chainMS.service.storeConfig.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeConfig.data.StoreConfigCacheDAO;
import com.hq.storeClient.service.storeConfig.bs.StoreConfigClientMgr;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.List;

public class StoreConfigDataHolder {

	public static StoreConfigDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigDataHolder.class);
	}

	public StoreConfig getStoreConfig(long storeId) {
		StoreConfig data = StoreConfigCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreConfigClientMgr.getInstance().findStoreConfigByStoreId(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public List<StoreConfig> getStoreConfigs(String storeIds) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<StoreConfig>  data = StoreConfigClientMgr.getInstance().getStoreConfigs(storeIds);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
}
