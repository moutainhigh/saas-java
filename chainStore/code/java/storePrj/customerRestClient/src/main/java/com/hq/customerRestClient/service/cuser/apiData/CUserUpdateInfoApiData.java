package com.hq.customerRestClient.service.cuser.apiData;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CUserUpdateInfoApiData {
	private String name;

	private String headImg;

	private int gender;

	private int age;

	public static CUserUpdateInfoApiData newInstance() {
		return new CUserUpdateInfoApiData();
	}

	public void update(CUser user) {
		FastBeanCopyer.getInstance().copy(this, user);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
