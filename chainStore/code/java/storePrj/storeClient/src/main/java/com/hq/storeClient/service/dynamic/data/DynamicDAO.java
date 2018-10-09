package com.hq.storeClient.service.dynamic.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class DynamicDAO extends RestDao<Dynamic> {

	public static DynamicDAO getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<Dynamic> findPage(String findPath, DynamicQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Dynamic.class);
	}
	
	public PageResp<Dynamic> findPageForCuser(String findPath, DynamicQueryFormForCuser queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Dynamic.class);
	}

}
