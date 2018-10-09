package com.hq.chainMS.service.chainUser.apiData;

public class ChainUserUpdateInfoForm {
	private long id;
	private String name;
	private String headImg;
	private int gender;
	// 出生日期 格式 yyyyMMdd
	private String birthday;

	public static ChainUserUpdateInfoForm newInstance() {
		return new ChainUserUpdateInfoForm();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
