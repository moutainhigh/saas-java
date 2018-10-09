package com.hq.chainStore.service.storeVip.bs;

import com.hq.chainStore.service.storeVip.data.StoreVip;
import com.hq.chainStore.service.storeVip.data.StoreVipDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class StoreVipMgr {

	public static StoreVipMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipMgr.class);
	}
	
	public StoreVip checkExpired(long storeId) {
		final String findPath = "checkExpired";
		ReqMap reqMap = ReqMap.newInstance().add("storeId", storeId);
		return StoreVipDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}

}
