package com.hq.storeClient.service.opLog.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeClient.service.opLog.data.OpLog;
import com.hq.storeClient.service.opLog.data.OpLogDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogClientMgr {

	public static OpLogClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OpLogClientMgr.class);
	}

	public PageResp<OpLog> findPage(OpLogQueryForm queryForm) {
		final String findPath="findPage"; 
		return OpLogDAO.getInstance().findPage(findPath, queryForm);
	}
}
