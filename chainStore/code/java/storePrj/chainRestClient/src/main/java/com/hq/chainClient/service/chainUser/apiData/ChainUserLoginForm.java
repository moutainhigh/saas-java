package com.hq.chainClient.service.chainUser.apiData;

public class ChainUserLoginForm {
	private long phone;
	private String password;

	public static ChainUserLoginForm newInstance() {
		return new ChainUserLoginForm();
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

}
