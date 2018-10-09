package com.hq.storeMS.service.buser.apiData;

public class BUserChangePasswordApiData {
	
	public static BUserChangePasswordApiData newInstance(){
		return new BUserChangePasswordApiData();
	}
	
	private long buserId;
	
	private String password;
	
	private String oldPassword;

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
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
