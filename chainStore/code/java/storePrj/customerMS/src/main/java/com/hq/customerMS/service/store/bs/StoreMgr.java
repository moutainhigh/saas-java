package com.hq.customerMS.service.store.bs;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMgr {

	public static StoreMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMgr.class);
	}
	
	public Store get(long id) {
		return StoreDataHolder.getInstance().get(id);
	}
	
	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm){
		return StoreDataHolder.getInstance().findStoreByCond(queryForm);
	}
	
	public void joinStore(JoinStoreForm joinStoreForm){
		StoreDataHolder.getInstance().joinStore(joinStoreForm);
	}
	
	public List<Store> findMyStores(long cuserId){
		return StoreDataHolder.getInstance().findMyStores(cuserId);
	}
	
}
