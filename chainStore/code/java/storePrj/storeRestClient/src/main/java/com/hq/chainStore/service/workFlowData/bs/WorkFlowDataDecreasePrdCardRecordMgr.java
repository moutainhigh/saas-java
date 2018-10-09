package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataDecreasePrdCardRecordMgr {

	public static WorkFlowDataDecreasePrdCardRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataDecreasePrdCardRecordMgr.class);
	}
	
	private final String module = "decreasePrdCard";
	
	public WorkFlowData updateDecreasePrdCardRecord(long workFlowDataId, String decreasePrdCardId, DecreasePrdCardUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, decreasePrdCardId, inputForm, module);
	}
	
	public WorkFlowData deleteDecreasePrdCardRecord(long workFlowDataId, String decreasePrdCardId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, decreasePrdCardId, module);
	}
	
	public WorkFlowData addDecreasePrdCardRecord(long workFlowDataId, DecreasePrdCardAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addDecreasePrdCardRecordList(long workFlowDataId, DecreasePrdCardAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updDecreasePrdCardRecordList(long workFlowDataId, DecreasePrdCardUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
