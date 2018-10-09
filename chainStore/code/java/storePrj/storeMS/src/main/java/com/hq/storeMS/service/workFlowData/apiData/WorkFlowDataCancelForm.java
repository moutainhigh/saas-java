package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.workFlowData.data.CancelReason;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataStatusEnum;

public class WorkFlowDataCancelForm {
	private String cancelReason;

	public static WorkFlowDataCancelForm newInstance() {
		return new WorkFlowDataCancelForm();
	}
	
	public void updateWorkFlowData(WorkFlowData workFlowData, BUser operator) {
		CancelReason data = CancelReason.newInstance();
		data.setCancelReason(cancelReason);
		data.setOperator(operator.getName());
		data.setOperatorId(operator.getId());
		workFlowData.setStatus(WorkFlowDataStatusEnum.CANCEL.ordinal());
		workFlowData.setCancelReason(data);
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}
