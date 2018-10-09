package com.hq.chainMS.service.store.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.store.data.StoreCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.apiData.StoreUpdateChainData;
import com.hq.storeClient.service.store.bs.StoreClientMgr;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreDataHolder {

	public static StoreDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreDataHolder.class);
	}

	public Store get(long storeId) {
		Store data = StoreCacheDAO.getInstance().get(storeId);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Store> page = StoreClientMgr.getInstance().findStoreByCond(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}

	public void updateChainData(long storeId, StoreUpdateChainData inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreClientMgr.getInstance().updateChainData(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
}
