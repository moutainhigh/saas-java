package com.hq.customerMS.service.cuser.apiData;

public class CUserChangePasswordApiData {

	public static CUserChangePasswordApiData newInstance() {
		return new CUserChangePasswordApiData();
	}

	private String password;

	private String oldPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
