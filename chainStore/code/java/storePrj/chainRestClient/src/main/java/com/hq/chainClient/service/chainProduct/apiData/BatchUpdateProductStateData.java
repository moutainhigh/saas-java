package com.hq.chainClient.service.chainProduct.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BatchUpdateProductStateData {
	private Set<String> prdIdSet = new HashSet<String>();
	private int state;

	public static BatchUpdateProductStateData newInstance() {
		return new BatchUpdateProductStateData();
	}

	public List<UpdateProductStateData> toUpdateProductStateDataList() {
		List<UpdateProductStateData> list = new ArrayList<UpdateProductStateData>();
		for (String id : prdIdSet) {
			list.add(UpdateProductStateData.newInstance(id, state));
		}
		return list;
	}

	public Set<String> getPrdIdSet() {
		return prdIdSet;
	}

	public void setPrdIdSet(Set<String> prdIdSet) {
		this.prdIdSet = prdIdSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
