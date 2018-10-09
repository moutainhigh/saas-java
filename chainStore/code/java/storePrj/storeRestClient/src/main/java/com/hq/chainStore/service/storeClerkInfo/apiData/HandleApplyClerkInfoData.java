package com.hq.chainStore.service.storeClerkInfo.apiData;

public class HandleApplyClerkInfoData {

	
	private long storeId;
	private long bUserId;
	private boolean approved;
	
	
	public static HandleApplyClerkInfoData newInstance(){
		return new HandleApplyClerkInfoData();
	}
	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getbUserId() {
		return bUserId;
	}
	public void setbUserId(long bUserId) {
		this.bUserId = bUserId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
	
}
