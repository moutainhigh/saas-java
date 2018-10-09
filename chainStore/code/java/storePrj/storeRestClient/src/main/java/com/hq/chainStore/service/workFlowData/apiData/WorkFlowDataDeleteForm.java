package com.hq.chainStore.service.workFlowData.apiData;

public class WorkFlowDataDeleteForm {
	private long workFlowDataId;

	public static WorkFlowDataDeleteForm newInstance() {
		return new WorkFlowDataDeleteForm();
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}
}
