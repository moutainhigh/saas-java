package com.hq.chainStore.service.storeVipType.bs;

import java.util.List;

import com.hq.chainStore.service.storeVipType.data.StoreVipType;
import com.hq.chainStore.service.storeVipType.data.StoreVipTypeDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class StoreVipTypeMgr {

	public static StoreVipTypeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipTypeMgr.class);
	}
	
	public StoreVipType get(long id) {
		return StoreVipTypeDAO.getInstance().get(id);
	}
	
	public List<StoreVipType> findPage(int pageItemCount, int pageNo) {
		final String findPath = "findPage";
		ReqMap reqMap = ReqMap.newInstance();
		return StoreVipTypeDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
	}
	
}
