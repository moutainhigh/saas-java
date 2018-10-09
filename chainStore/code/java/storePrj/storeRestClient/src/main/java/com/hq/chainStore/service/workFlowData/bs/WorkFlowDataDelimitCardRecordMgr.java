package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataDelimitCardRecordMgr {

	public static WorkFlowDataDelimitCardRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataDelimitCardRecordMgr.class);
	}
	
	private final String module = "delimitCardRecord";
	
	public WorkFlowData updateDelimitCardRecord(long workFlowDataId, String delimitCardId, DelimitCardRecordUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, delimitCardId, inputForm, module);
	}
	
	public WorkFlowData deleteDelimitCardRecord(long workFlowDataId, String delimitCardId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, delimitCardId, module);
	}
	
	public WorkFlowData addDelimitCardRecord(long workFlowDataId, DelimitCardRecordAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addDelimitCardRecordList(long workFlowDataId, DelimitCardRecordAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updDelimitCardRecordList(long workFlowDataId, DelimitCardRecordUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
