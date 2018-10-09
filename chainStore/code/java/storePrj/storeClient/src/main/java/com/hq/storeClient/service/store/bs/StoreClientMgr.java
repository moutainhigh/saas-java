package com.hq.storeClient.service.store.bs;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeClient.service.store.apiData.StoreUpdateChainData;
import com.hq.storeClient.service.store.apiData.StoreUpdateType;
import com.hq.storeClient.service.store.data.Store;
import com.hq.storeClient.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class StoreClientMgr {

	public static StoreClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClientMgr.class);
	}
	
	public Store get(long storeId) {
		return StoreDAO.getInstance().get(storeId);
	}
	
	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm) {
		String findPath="findStoreByCond";
		return StoreDAO.getInstance().findStoreByCond(findPath, queryForm);
	}
	
	public void joinStoreForCuser(JoinStoreForm joinStoreForm){
		String actionName="joinStoreForCuser";
		StoreDAO.getInstance().rawReq(actionName, joinStoreForm);
	}
	
	public List<Store> findByCuser(long cuserId){
		String actionName = "findByCuser";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cuserId", cuserId);
		return StoreDAO.getInstance().findWithReqParam(actionName,reqMap, 1000, 1);
	}
	
	public void updateChainData(long storeId, StoreUpdateChainData inputForm){
		StoreUpdateApiForm updateForm = StoreUpdateApiForm.newInstance();
		updateForm.setUpdateType(StoreUpdateType.StoreUpdateChainData.ordinal());
		updateForm.setUpdateChainData(inputForm);
		update(storeId, updateForm);
	}
	
	public void update(long storeId, StoreUpdateApiForm updateForm) {
		StoreDAO.getInstance().update(storeId, updateForm);
	}
}
