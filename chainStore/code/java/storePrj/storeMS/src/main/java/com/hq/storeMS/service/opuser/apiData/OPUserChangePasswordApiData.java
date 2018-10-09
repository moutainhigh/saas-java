package com.hq.storeMS.service.opuser.apiData;

public class OPUserChangePasswordApiData {
	
	public static OPUserChangePasswordApiData newInstance(){
		return new OPUserChangePasswordApiData();
	}
	
	private long opuserId;
	
	private String password;

	public long getBuserId() {
		return opuserId;
	}

	public void setBuserId(long opuserId) {
		this.opuserId = opuserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
