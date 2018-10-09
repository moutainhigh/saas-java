package com.hq.storeClient.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerInfo {
	// 客户ID
	private String leaguerId;
	// 跟进人员
	private long followUserId;

	public static LeaguerInfo newInstance() {
		return new LeaguerInfo();
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
