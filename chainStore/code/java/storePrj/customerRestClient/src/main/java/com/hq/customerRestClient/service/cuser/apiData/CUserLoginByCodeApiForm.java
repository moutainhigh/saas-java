package com.hq.customerRestClient.service.cuser.apiData;


public class CUserLoginByCodeApiForm {
	// 手机号码
	private long phone;
	// 短信验证码
	private String verifyCode;

	public static CUserLoginByCodeApiForm newInstance() {
		CUserLoginByCodeApiForm data = new CUserLoginByCodeApiForm();
		return data;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
