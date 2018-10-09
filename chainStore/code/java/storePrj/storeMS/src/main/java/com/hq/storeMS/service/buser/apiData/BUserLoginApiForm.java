package com.hq.storeMS.service.buser.apiData;

public class BUserLoginApiForm {
	
	private long phone;
	
	private String password;
	
	public static BUserLoginApiForm newInstance(){
		return new BUserLoginApiForm();
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
