package com.hq.storeMS.service.store.apiData;

public class Submit4CheckApiData {
	
	private long storeId;

	public static Submit4CheckApiData newInstance(){
		return new Submit4CheckApiData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	
}
