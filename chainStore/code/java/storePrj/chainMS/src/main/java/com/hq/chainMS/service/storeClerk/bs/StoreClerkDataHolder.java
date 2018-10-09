package com.hq.chainMS.service.storeClerk.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeClerk.data.StoreClerkInfoCacheDAO;
import com.hq.storeClient.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeClient.service.storeClerkInfo.bs.StoreClerkInfoClientMgr;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkDataHolder {

	public static StoreClerkDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkDataHolder.class);
	}

	public StoreClerkInfo getStoreClerk(long storeId) {
		StoreClerkInfo data = StoreClerkInfoCacheDAO.getInstance().get(StoreClerkInfo.getIdByStoreId(storeId));
		if (data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreClerkInfoClientMgr.getInstance().getByStoreId(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public void applyClerkInfo(long storeId, ApplyClerkInfoData applyClerkInfoData) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreClerkInfoClientMgr.getInstance().applyClerkInfo(storeId, applyClerkInfoData);
		AppIdThreadLocal.getInstance().set(null);
	}

}
