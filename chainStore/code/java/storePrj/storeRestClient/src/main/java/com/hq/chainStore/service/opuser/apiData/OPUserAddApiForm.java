package com.hq.chainStore.service.opuser.apiData;

public class OPUserAddApiForm {
	
	
	private String name;
	
	private long phone;
	
	private String password;
	
	//3为店铺审核
	private int OPAdminPrem; 
	

	public static OPUserAddApiForm newInstance(){
		return new OPUserAddApiForm();
	}
	
	public String getName() {
		return name;
	}

	public OPUserAddApiForm setName(String name) {
		this.name = name;
		return this;
	}
	
	public long getPhone() {
		return phone;
	}

	public OPUserAddApiForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public OPUserAddApiForm setPassword(String password) {
		this.password = password;
		return this;
	}

	public int getOPAdminPrem() {
		return OPAdminPrem;
	}

	public void setOPAdminPrem(int oPAdminPrem) {
		OPAdminPrem = oPAdminPrem;
	}


	
}
