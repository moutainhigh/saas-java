package com.hq.chainClient.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

public class BatchUpdMemberCardStateData {
	private Set<String> mbCardIdSet = new HashSet<String>();
	private int state;

	public static BatchUpdMemberCardStateData newInstance() {
		return new BatchUpdMemberCardStateData();
	}

	public Set<String> getMbCardIdSet() {
		return mbCardIdSet;
	}

	public void setMbCardIdSet(Set<String> mbCardIdSet) {
		this.mbCardIdSet = mbCardIdSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
