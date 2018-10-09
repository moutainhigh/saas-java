package com.hq.chainStore.service.opuser.apiData;

public class OPUserLoginApiForm {
	
	private long phone;
	
	private String password;
	
	public static OPUserLoginApiForm newInstance(){
		return new OPUserLoginApiForm();
	}
	

	public long getPhone() {
		return phone;
	}

	public OPUserLoginApiForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public OPUserLoginApiForm setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	
}
