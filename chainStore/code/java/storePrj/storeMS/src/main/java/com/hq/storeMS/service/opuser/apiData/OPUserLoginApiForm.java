package com.hq.storeMS.service.opuser.apiData;

public class OPUserLoginApiForm {
	
	private String name;
	
	private String password;
	
	public static OPUserLoginApiForm newInstance(){
		return new OPUserLoginApiForm();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
