package com.hq.storeMS.service.storeClerkInfo.apiData;

public class AddRole2ClerkData {

	private long storeId;
	private long buserId;
	private int roleId;

	public static AddRole2ClerkData newInstance(){
		return new AddRole2ClerkData();
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
	
}



