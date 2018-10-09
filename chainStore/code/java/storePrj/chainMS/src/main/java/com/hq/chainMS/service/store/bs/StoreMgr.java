package com.hq.chainMS.service.store.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.apiData.StoreUpdateChainData;
import com.hq.storeClient.service.store.data.Store;
import com.hq.storeClient.service.store.data.StoreOperateEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMgr {

	public static StoreMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMgr.class);
	}

	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm) {
		return StoreDataHolder.getInstance().findStoreByCond(queryForm);
	}

	public void updateChainData(long storeId, StoreUpdateChainData inputForm) {
		StoreDataHolder.getInstance().updateChainData(storeId, inputForm);
	}
	
	public void relieveStore(long storeId, long chainId) {
		StoreUpdateChainData updateForm = StoreUpdateChainData.newInstance();
		updateForm.setChainId(chainId);
		updateForm.setStoreId(storeId);
		updateForm.setOperate(StoreOperateEnum.RelieveStore.ordinal());
		updateChainData(storeId, updateForm);
	}
	
	public void joinChainHandler(long storeId, long chainId) {
		StoreUpdateChainData updateForm = StoreUpdateChainData.newInstance();
		updateForm.setChainId(chainId);
		updateForm.setStoreId(storeId);
		updateForm.setOperate(StoreOperateEnum.JoinChainHandler.ordinal());
		updateChainData(storeId, updateForm);
	}

	public Store get(long storeId) {
		return StoreDataHolder.getInstance().get(storeId);
	}

}
