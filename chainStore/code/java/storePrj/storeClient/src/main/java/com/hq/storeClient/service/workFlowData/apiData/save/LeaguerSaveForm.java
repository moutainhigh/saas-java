package com.hq.storeClient.service.workFlowData.apiData.save;

public class LeaguerSaveForm {
	// 客户ID
	private String leaguerId;
	// 跟进人员
	private long followUserId;

	public static LeaguerSaveForm newInstance() {
		return new LeaguerSaveForm();
	}
	
	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(long followUserId) {
		this.followUserId = followUserId;
	}
}
