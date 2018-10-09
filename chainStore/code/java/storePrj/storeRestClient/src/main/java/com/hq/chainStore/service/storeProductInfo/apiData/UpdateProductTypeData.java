package com.hq.chainStore.service.storeProductInfo.apiData;


public class UpdateProductTypeData {

	private String id;
	
	private long storeId;
	
	private String name;
	
	
	public static UpdateProductTypeData newInstance(){
		return new UpdateProductTypeData();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
