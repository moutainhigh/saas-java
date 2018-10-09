package com.hq.customerRestClient.service.cuser.apiData;

public class CUserChangeDefaultAddressForm {
	private long addressId;
	
	public static CUserChangeDefaultAddressForm newInstance() {
		return new CUserChangeDefaultAddressForm();
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
}
