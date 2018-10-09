package com.hq.storeClient.service.workFlowData.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class WorkFlowDataDAO extends RestDao<WorkFlowData> {

	public static WorkFlowDataDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<WorkFlowData> findWorkFlowDataPageInfo(String findPath, WorkFlowDataQueryForm queryForm){
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), WorkFlowData.class);
	}
}
