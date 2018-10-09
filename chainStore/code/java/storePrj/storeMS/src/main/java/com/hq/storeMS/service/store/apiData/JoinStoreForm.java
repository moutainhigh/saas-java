package com.hq.storeMS.service.store.apiData;

public class JoinStoreForm {
	private long storeId;
	
	private long cuserId;

	public static JoinStoreForm newInstance() {
		return new JoinStoreForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	
}
