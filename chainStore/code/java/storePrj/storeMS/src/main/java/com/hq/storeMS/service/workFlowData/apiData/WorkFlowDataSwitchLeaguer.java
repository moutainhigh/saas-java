package com.hq.storeMS.service.workFlowData.apiData;

public class WorkFlowDataSwitchLeaguer {
	// 客户ID
	private String leaguerId;
	// 工作流ID
	private long workFlowDataId;

	public static WorkFlowDataSwitchLeaguer newInstance() {
		return new WorkFlowDataSwitchLeaguer();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}
}
