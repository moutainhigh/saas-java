package com.hq.storeManagerRestClient.service.muser.apiData;

public class MUserLoginApiForm {
	
	private String account;
	
	private String password;
	
	public static MUserLoginApiForm newInstance(){
		return new MUserLoginApiForm();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
