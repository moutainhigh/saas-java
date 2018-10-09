package com.hq.storeMS.service.storeProductInfo.apiData;

public class RemoveProductInfoData {
	private long storeId;
	private String id;
	
	public static RemoveProductInfoData newInstance(){
		return new RemoveProductInfoData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
