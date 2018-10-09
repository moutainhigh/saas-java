package com.hq.customerMS.service.cuser.apiData;

import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CUserAddByWxForm {
	// 昵称
	private String name;
	// 头像
	private String headImg;
	// 性别
	private int gender;
	// 年龄
	private int age;
	// unionId
	private String wxUnionId;

	public static CUserAddByWxForm newInstance() {
		CUserAddByWxForm data = new CUserAddByWxForm();
		return data;
	}

	public CUser toUser() {
		CUser user = CUser.newInstance();
		FastBeanCopyer.getInstance().copy(this, user);
		return user;
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

	public String getWxUnionId() {
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}
}
