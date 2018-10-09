package com.hq.chainClient.service.chainProduct.apiData;

import java.util.HashSet;
import java.util.Set;

public class BatchUpdateProductTypeStateData {
	private Set<String> prdTypeIdSet = new HashSet<String>();
	private int state;

	public static BatchUpdateProductTypeStateData newInstance() {
		return new BatchUpdateProductTypeStateData();
	}

	public Set<String> getPrdTypeIdSet() {
		return prdTypeIdSet;
	}

	public void setPrdTypeIdSet(Set<String> prdTypeIdSet) {
		this.prdTypeIdSet = prdTypeIdSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
