package com.hq.storeMS.service.workFlowData.apiData.save;

import com.hq.storeMS.service.workFlowData.data.LeaguerInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerSaveForm {
	// 客户ID
	private String leaguerId;
	// 跟进人员
	private long followUserId;

	public static LeaguerSaveForm newInstance() {
		return new LeaguerSaveForm();
	}
	
	public LeaguerInfo toLeaguerInfo() {
		LeaguerInfo data = LeaguerInfo.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
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
