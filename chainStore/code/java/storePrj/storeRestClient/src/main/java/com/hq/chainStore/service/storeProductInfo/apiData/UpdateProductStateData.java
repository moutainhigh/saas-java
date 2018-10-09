package com.hq.chainStore.service.storeProductInfo.apiData;

public class UpdateProductStateData {

	private String id;
	
	private long storeId;
	
	private int state;
	
	public static UpdateProductStateData newInstance(){
		return new UpdateProductStateData();
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
