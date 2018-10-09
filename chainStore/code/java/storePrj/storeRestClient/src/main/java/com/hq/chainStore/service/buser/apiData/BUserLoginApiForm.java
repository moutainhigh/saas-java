package com.hq.chainStore.service.buser.apiData;

public class BUserLoginApiForm {
	
	private long phone;
	
	private String password;
	
	public static BUserLoginApiForm newInstance(){
		return new BUserLoginApiForm();
	}
	

	public long getPhone() {
		return phone;
	}

	public BUserLoginApiForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public BUserLoginApiForm setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	
}
