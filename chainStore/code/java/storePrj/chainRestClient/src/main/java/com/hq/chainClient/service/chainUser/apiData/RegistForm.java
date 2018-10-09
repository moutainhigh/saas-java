package com.hq.chainClient.service.chainUser.apiData;

public class RegistForm {
	// 名称
	private String name;
	// 手机号码
	private long phone;
	// 密码
	private String password;
	// 验证码
	private String verifyCode;
	// 手机号码区号
	private String areaCode;
	// 性别 对应 GenderEnum
	private int gender;

	public static RegistForm newInstance() {
		return new RegistForm();
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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

}
