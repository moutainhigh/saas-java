package com.hq.chainStore.service.storeLeaguerInfo.apiData;

import java.util.HashSet;
import java.util.Set;

public class SaveFollowUserForm {
	private String leaguerId;
	private Set<Long> buserIds = new HashSet<Long>();

	public static SaveFollowUserForm newInstance() {
		return new SaveFollowUserForm();
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

}
