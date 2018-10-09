package com.hq.storeMS.service.workFlowData.bs.updateHandle;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataCancelForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataDeleteForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateStatusForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataMgr;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataUpdateHandle {

	public static WorkFlowDataUpdateHandle getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataUpdateHandle.class);
	}
	
	public OperateTips updateWorkFlowDataInfo(long workFlowDataId, WorkFlowDataUpdateInfoForm updateInfoForm) {
		OperateTips operateTips = OperateTips.newInstance(false,"更新工作流信息失败");
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		FastBeanCopyer.getInstance().copy(updateInfoForm, workFlowData);
		WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	public OperateTips updateWorkFlowDataStatus(long workFlowDataId, WorkFlowDataUpdateStatusForm updateStatusForm) {
		OperateTips operateTips = OperateTips.newInstance(false,"更新工作流状态失败");
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		FastBeanCopyer.getInstance().copy(updateStatusForm, workFlowData);
		WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	public OperateTips deleteWorkFlowData(long workFlowDataId, WorkFlowDataDeleteForm deleteForm) {
		OperateTips operateTips = OperateTips.newInstance(false,"删除流程失败");
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		workFlowData.setEntityState(EntityState.Deleted.ordinal());
		WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	public OperateTips cancelWorkFlowData(long workFlowDataId, WorkFlowDataCancelForm cancelForm) {
		OperateTips operateTips = OperateTips.newInstance(false,"流程作废操作失败");
		BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		cancelForm.updateWorkFlowData(workFlowData, sessionBUser);
		WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
		operateTips.setSuccess(true);
		return operateTips;
	}
}
