package com.hq.chainClient.service.store.bs;

import com.hq.chainClient.common.exception.AppNameAuthUtils;
import com.hq.chainClient.common.exception.AppNameEnum;
import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.store.apiData.StoreQueryForm;
import com.hq.chainClient.service.store.data.Store;
import com.hq.chainClient.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClientMgr {

	public static StoreClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClientMgr.class);
	}
	
	public Store get(long id) {
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMS);
		
		return StoreDAO.getInstance().get(id);
	}
	
	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm) {
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMS);
		
		String findPath="findStoreByCond";
		return StoreDAO.getInstance().findStoreByCond(findPath, queryForm);
	}
}
