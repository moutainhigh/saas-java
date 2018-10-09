package com.hq.storeClient.service.spreadStatis.bs;

import com.hq.storeClient.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeClient.service.spreadStatis.data.SpreadStatisDAO;
import com.hq.storeClient.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeClient.service.spreadStatis.data.vo.StoreSpreadStatis;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisClientMgr {
	public static SpreadStatisClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisClientMgr.class);
	}
	
	public BuserSpreadStatis findBuserSpreadStatis(SpreadStatisQueryForm queryForm) {
		final String findPath = "findBuserSpreadStatis";
		return SpreadStatisDAO.getInstance().findBuserSpreadStatis(findPath, queryForm);
	}
	
	public StoreSpreadStatis findStoreSpreadStatis(SpreadStatisQueryForm queryForm) {
		final String findPath = "findStoreSpreadStatis";
		return SpreadStatisDAO.getInstance().findStoreSpreadStatis(findPath, queryForm);
	}
}
