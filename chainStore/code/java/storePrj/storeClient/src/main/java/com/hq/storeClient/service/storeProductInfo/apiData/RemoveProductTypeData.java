package com.hq.storeClient.service.storeProductInfo.apiData;

public class RemoveProductTypeData {
	private String id;
	private long storeId;
	
	public static RemoveProductTypeData newInstance(){
		return new RemoveProductTypeData();
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
