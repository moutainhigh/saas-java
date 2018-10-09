package com.hq.storeMS.service.workFlowData.apiData;

public class WorkFlowDataUpdateStatusForm {
	private int status;

	public static WorkFlowDataUpdateStatusForm newInstance() {
		return new WorkFlowDataUpdateStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
