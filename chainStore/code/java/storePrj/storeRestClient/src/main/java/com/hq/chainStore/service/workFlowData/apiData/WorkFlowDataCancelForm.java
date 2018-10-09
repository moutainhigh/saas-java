package com.hq.chainStore.service.workFlowData.apiData;

public class WorkFlowDataCancelForm {
	private String cancelReason;

	public static WorkFlowDataCancelForm newInstance() {
		return new WorkFlowDataCancelForm();
	}
	
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}
