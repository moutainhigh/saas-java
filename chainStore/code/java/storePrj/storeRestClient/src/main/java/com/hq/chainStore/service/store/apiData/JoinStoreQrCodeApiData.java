package com.hq.chainStore.service.store.apiData;

public class JoinStoreQrCodeApiData {
	
	private long storeId;

	public static JoinStoreQrCodeApiData newInstance(){
		return new JoinStoreQrCodeApiData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	
}
