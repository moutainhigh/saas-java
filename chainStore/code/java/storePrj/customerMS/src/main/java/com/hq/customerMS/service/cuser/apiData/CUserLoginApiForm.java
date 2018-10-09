package com.hq.customerMS.service.cuser.apiData;

public class CUserLoginApiForm {

	private long phone;

	private String password;

	public static CUserLoginApiForm newInstance() {
		return new CUserLoginApiForm();
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
