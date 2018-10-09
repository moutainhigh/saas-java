package com.hq.storeMS.service.opuser.apiData;


import com.hq.storeMS.service.opuser.data.OPUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class OPUserAddApiForm {
	
	private String name;
	
	private long phone;
	
	private String password;

	public static OPUserAddApiForm newInstance(){
		return new OPUserAddApiForm();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public OPUser toOPUser(){
		OPUser opuser = new OPUser();
		FastBeanCopyer.getInstance().copy(this, opuser);
		return opuser;
	}
	
}
