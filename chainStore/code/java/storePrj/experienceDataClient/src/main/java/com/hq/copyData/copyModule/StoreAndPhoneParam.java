package com.hq.copyData.copyModule;

public class StoreAndPhoneParam {
	private long storeId;
	private long phone;

	public static StoreAndPhoneParam newInstance(long storeIdP, long phoneP) {
		StoreAndPhoneParam data = new StoreAndPhoneParam();
		data.storeId = storeIdP;
		data.phone = phoneP;
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
}
