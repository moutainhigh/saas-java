package com.hq.customerMS.service.cuser.apiData;


public class CUserAddressRemoveForm {
	private long addressId;
	
	public static CUserAddressRemoveForm newInstance() {
		return new CUserAddressRemoveForm();
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	
}
