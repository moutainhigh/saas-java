package com.hq.chainStore.service.storeClerkInfo.apiData;

import java.util.HashSet;
import java.util.Set;

public class HandleGroupApplyClerkData {

	
	private long storeId;
	private Set<Long> buserIdSet= new HashSet<Long>();
	private boolean approved;
	
	
	public static HandleGroupApplyClerkData newInstance(){
		return new HandleGroupApplyClerkData();
	}
	
	public long getStoreId() {
		return storeId;
	}
	
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Set<Long> getBuserIdSet() {
		return buserIdSet;
	}

	public void setBuserIdSet(Set<Long> buserIdSet) {
		this.buserIdSet = buserIdSet;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
	
}
