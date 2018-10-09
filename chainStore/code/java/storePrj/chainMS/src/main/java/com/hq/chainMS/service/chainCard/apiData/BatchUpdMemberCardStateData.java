package com.hq.chainMS.service.chainCard.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BatchUpdMemberCardStateData {
	private Set<String> mbCardIdSet = new HashSet<String>();
	private int state;

	public static BatchUpdMemberCardStateData newInstance() {
		return new BatchUpdMemberCardStateData();
	}

	public List<UpdMemberCardStateData> toUpdMemberCardStateDataList() {
		List<UpdMemberCardStateData> list = new ArrayList<UpdMemberCardStateData>();
		for (String id : this.mbCardIdSet) {
			list.add(UpdMemberCardStateData.newInstance(id, this.state));
		}
		return list;
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
