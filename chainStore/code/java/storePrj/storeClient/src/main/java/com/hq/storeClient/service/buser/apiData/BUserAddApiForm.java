package com.hq.storeClient.service.buser.apiData;


import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BUserAddApiForm {
	//名称
	private String name;
	//手机号码
	private long phone;
	//密码
	private String password;
	//头像
	private String headImg;
	//性别
	private int gender;
	//年龄
	private int age;
	//角色
	private Set<Integer> roleSet = new HashSet<Integer>();
	//验证码
	private String verifyCode;
	//激活码
	private String activateCode;
	//手机号码区号
	private String areaCode;
	

	public static BUserAddApiForm newInstance(){
		return new BUserAddApiForm();
	}
	
	public BUser toBUser(){
		BUser buser = BUser.newInstance();
		FastBeanCopyer.getInstance().copy(this, buser);
		return buser;
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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
}
