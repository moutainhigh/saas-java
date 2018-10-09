package com.hq.chainStore.service.store.apiData;

public class StoreUpdateStatusData {
	private long storeId;
	private int state;
	private String password;

	public static StoreUpdateStatusData newInstance() {
		return new StoreUpdateStatusData();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
