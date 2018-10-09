package com.hq.chainClient.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

public class BatchUpdProductCardStateData {
	private Set<String> prdCardIdSet = new HashSet<String>();// 次卡id
	private int state;

	public static BatchUpdProductCardStateData newInstance() {
		return new BatchUpdProductCardStateData();
	}

	public Set<String> getPrdCardIdSet() {
		return prdCardIdSet;
	}

	public void setPrdCardIdSet(Set<String> prdCardIdSet) {
		this.prdCardIdSet = prdCardIdSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
