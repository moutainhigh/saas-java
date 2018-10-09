package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataPrdCardRecordMgr {

	public static WorkFlowDataPrdCardRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataPrdCardRecordMgr.class);
	}
	
	private final String module = "prdCard";
	
	public WorkFlowData updatePrdCardRecord(long workFlowDataId, String prdCardId, PrdCardUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, prdCardId, inputForm, module);
	}
	
	public WorkFlowData deletePrdCardRecord(long workFlowDataId, String prdCardId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, prdCardId, module);
	}
	
	public WorkFlowData addPrdCardRecord(long workFlowDataId, PrdCardAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addPrdCardRecordList(long workFlowDataId, PrdCardAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updPrdCardRecordList(long workFlowDataId, PrdCardUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
