package com.hq.storeMS.service.storeProductInfo.apiData;

public class UpdateProductStateData {

	private String id;
	
	private long storeId;
	
	private int state;
	
	public static UpdateProductStateData newInstance(){
		return new UpdateProductStateData();
	}
	
	public static UpdateProductStateData newInstance(String idP, long storeIdP, int stateP){
		UpdateProductStateData data = newInstance();
		data.id = idP;
		data.storeId = storeIdP;
		data.state = stateP;
		return data;
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
