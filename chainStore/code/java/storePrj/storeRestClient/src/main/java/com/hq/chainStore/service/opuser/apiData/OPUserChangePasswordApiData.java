package com.hq.chainStore.service.opuser.apiData;

public class OPUserChangePasswordApiData {
	
	public static OPUserChangePasswordApiData newInstance(){
		return new OPUserChangePasswordApiData();
	}
	
	private long opuserId;
	
	private String password;


	public long getOpuserId() {
		return opuserId;
	}

	public void setOpuserId(long opuserId) {
		this.opuserId = opuserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
