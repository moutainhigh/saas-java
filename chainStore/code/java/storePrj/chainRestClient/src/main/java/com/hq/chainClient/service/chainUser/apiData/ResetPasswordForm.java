package com.hq.chainClient.service.chainUser.apiData;

public class ResetPasswordForm {
	private long phone;
	private String password;
	private String verifyCode;
	
	public static ResetPasswordForm newInstance() {
		return new ResetPasswordForm();
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
