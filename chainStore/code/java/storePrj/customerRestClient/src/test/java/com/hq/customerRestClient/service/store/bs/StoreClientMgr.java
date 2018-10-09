package com.hq.customerRestClient.service.store.bs;

import java.util.List;

import com.hq.customerRestClient.service.store.apiData.JoinStoreForm;
import com.hq.customerRestClient.service.store.data.Store;
import com.hq.customerRestClient.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class StoreClientMgr {

	public static StoreClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClientMgr.class);
	}

	public List<Store> findByCuser(long cuserId){
		String actionName = "findMyStores";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cuserId", cuserId);
		return StoreDAO.getInstance().findWithReqParam(actionName,reqMap, 1000, 1);
	}
	
	public Store getStore(long storeId){
		return StoreDAO.getInstance().get(storeId);
	}
	
	public void joinStore(JoinStoreForm joinStoreForm){
		String actionName = "joinStore";
		StoreDAO.getInstance().rawReq(actionName, joinStoreForm);
	}
}
