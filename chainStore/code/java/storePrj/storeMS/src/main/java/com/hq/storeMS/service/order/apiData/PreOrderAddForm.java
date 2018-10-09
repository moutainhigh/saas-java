package com.hq.storeMS.service.order.apiData;

public class PreOrderAddForm {
	
	private long workFlowDataId;
	
	public static PreOrderAddForm newInstance() {
		return new PreOrderAddForm();
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}

}
