package com.hq.customerMS.service.sms.apiData;

public class RandomCodeAPIForm {
	private String phoneNumber;// 单个号码

	public static RandomCodeAPIForm newInstance() {
		return new RandomCodeAPIForm();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
