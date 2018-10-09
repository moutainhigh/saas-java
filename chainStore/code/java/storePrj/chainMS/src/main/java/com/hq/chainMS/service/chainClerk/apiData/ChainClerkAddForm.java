package com.hq.chainMS.service.chainClerk.apiData;

public class ChainClerkAddForm {
	private long chainId;
	// 名称
	private String name;
	// 手机号码
	private long phone;
	// 验证码
	private String verifyCode;
	// 手机号码区号
	private String areaCode;
	// 性别 对应 GenderEnum
	private int gender;
	// 密码
	private String password;
	//是否是跨店员工 对应 CrossClerkEnum
	private int crossClerk;

	public static ChainClerkAddForm newInstance() {
		return new ChainClerkAddForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getCrossClerk() {
		return crossClerk;
	}

	public void setCrossClerk(int crossClerk) {
		this.crossClerk = crossClerk;
	}

}
