package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataPackagePrjRecordMgr {

	public static WorkFlowDataPackagePrjRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataPackagePrjRecordMgr.class);
	}
	
	private final String module = "packagePrjRecord";
	
	public WorkFlowData updatePackagePrjRecord(long workFlowDataId, String packagePrjId, PackagePrjRecordUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, packagePrjId, inputForm, module);
	}
	
	public WorkFlowData deletePackagePrjRecord(long workFlowDataId, String packagePrjId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, packagePrjId, module);
	}
	
	public WorkFlowData addPackagePrjRecord(long workFlowDataId, PackagePrjRecordAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addPackagePrjRecordList(long workFlowDataId, PackagePrjRecordAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updPackagePrjRecordList(long workFlowDataId, PackagePrjRecordUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
