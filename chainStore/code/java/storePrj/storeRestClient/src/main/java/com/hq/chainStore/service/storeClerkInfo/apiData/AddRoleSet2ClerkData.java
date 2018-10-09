package com.hq.chainStore.service.storeClerkInfo.apiData;

import java.util.HashSet;
import java.util.Set;

public class AddRoleSet2ClerkData {

	private long storeId;
	private long buserId;
	private Set<Integer> roleIdSet = new HashSet<Integer>();
	

	public static AddRoleSet2ClerkData newInstance(){
		return new AddRoleSet2ClerkData();
	}

	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getBuserId() {
		return buserId;
	}
	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public Set<Integer> getRoleIdSet() {
		return roleIdSet;
	}


	public void setRoleIdSet(Set<Integer> roleIdSet) {
		this.roleIdSet = roleIdSet;
	}
	
	
	
	
}



