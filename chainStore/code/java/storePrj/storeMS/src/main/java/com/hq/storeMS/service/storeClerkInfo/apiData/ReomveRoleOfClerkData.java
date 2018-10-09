package com.hq.storeMS.service.storeClerkInfo.apiData;

public class ReomveRoleOfClerkData {

	private long storeId;
	private long buserId;
	private int roleId;
	
	public static ReomveRoleOfClerkData newInstance(){
		return new ReomveRoleOfClerkData();
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
