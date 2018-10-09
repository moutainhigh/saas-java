package com.hq.storeMS.service.storeClerkInfo.apiData;

public class ReomveClerkData {

	private long storeId;
	private long buserId;
	
	public static ReomveClerkData newInstance(){
		return new ReomveClerkData();
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
	
	
}
