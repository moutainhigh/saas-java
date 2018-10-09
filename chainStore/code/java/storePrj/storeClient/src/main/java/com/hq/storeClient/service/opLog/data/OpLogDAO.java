package com.hq.storeClient.service.opLog.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.opLog.apiData.OpLogQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class OpLogDAO extends RestDao<OpLog> {

	public static OpLogDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OpLogDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<OpLog> findPage(String findPath, OpLogQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), OpLog.class);
	}

}
