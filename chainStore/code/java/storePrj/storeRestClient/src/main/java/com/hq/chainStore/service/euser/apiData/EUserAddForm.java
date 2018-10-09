package com.hq.chainStore.service.euser.apiData;

public class EUserAddForm {

	private String name;

	private long phone;

	private int count;

	private String verifyCode;// 验证码

	public static EUserAddForm newInstance() {
		return new EUserAddForm();
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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
