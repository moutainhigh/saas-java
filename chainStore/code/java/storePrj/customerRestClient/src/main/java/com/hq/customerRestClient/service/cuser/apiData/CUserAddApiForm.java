package com.hq.customerRestClient.service.cuser.apiData;

import com.hq.customerRestClient.service.common.GenderEnum;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CUserAddApiForm {

	private String name;

	private long phone;

	private String password;

	private String headImg;

	private int gender;

	private int age;
	
	private String verifyCode;//验证码

	public static CUserAddApiForm newInstance() {
		CUserAddApiForm data = new CUserAddApiForm();
		data.name="未填写";
		data.gender=GenderEnum.Women.ordinal();
		data.age=18;
		return data;
	}
	
	public CUser toUser() {
		CUser user = CUser.newInstance();
		FastBeanCopyer.getInstance().copy(this, user);
		return user;
	}

	public void update(CUser user) {
		FastBeanCopyer.getInstance().copy(this, user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null){
			this.name = name;
		}
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
		if(gender != 0){
			this.gender = gender;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age != 0){
			this.age = age;
		}
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
