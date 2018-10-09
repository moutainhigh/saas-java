package com.hq.storeManagerRestClient.service.muser.apiData;


public class MUserAddApiForm {
	// 名称
	private String name;
	// 账号
	private String account;
	// 密码
	private String password;
	// 头像
	private String headImg;
	// 性别
	private int gender;

	public static MUserAddApiForm newInstance() {
		return new MUserAddApiForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

}
