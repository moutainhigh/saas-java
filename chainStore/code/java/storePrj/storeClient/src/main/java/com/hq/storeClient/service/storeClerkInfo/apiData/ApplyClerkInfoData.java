package com.hq.storeClient.service.storeClerkInfo.apiData;

public class ApplyClerkInfoData {
	private long storeId;
	private long bUserId;

	public static ApplyClerkInfoData newInstance() {
		return new ApplyClerkInfoData();
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

}
