package com.hq.storeClient.service.workFlowData.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeClient.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.storeClient.service.workFlowData.data.WorkFlowData;
import com.hq.storeClient.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class WorkFlowDataClientMgr {

	public static WorkFlowDataClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataClientMgr.class);
	}
	
	public PageResp<WorkFlowData> findWorkFlowDataPageInfo(WorkFlowDataQueryForm queryForm) {
		final String findPath = "findWorkFlowDataPageInfo";
		return WorkFlowDataDAO.getInstance().findWorkFlowDataPageInfo(findPath, queryForm);
	}
	
	public WorkFlowData getWorkFlowData(long workFlowDataId) {
		return WorkFlowDataDAO.getInstance().get(workFlowDataId);
	}
	
	public void deleteWorkFlowData(long workFlowDataId) {
		WorkFlowDataDAO.getInstance().delete(workFlowDataId);
	}
	
	public WorkFlowData saveOrUpdate(WorkFlowDataSaveForm inputForm) {
		final String actionName = "saveOrUpdate";
		RestResp resp = WorkFlowDataDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), WorkFlowData.class);
	}
}
