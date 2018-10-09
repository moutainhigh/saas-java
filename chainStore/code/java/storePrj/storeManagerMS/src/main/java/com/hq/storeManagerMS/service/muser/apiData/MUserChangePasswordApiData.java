package com.hq.storeManagerMS.service.muser.apiData;

public class MUserChangePasswordApiData {

	public static MUserChangePasswordApiData newInstance() {
		return new MUserChangePasswordApiData();
	}

	private long muserId;

	private String password;

	private String oldPassword;

	public long getMuserId() {
		return muserId;
	}

	public void setMuserId(long muserId) {
		this.muserId = muserId;
	}

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
