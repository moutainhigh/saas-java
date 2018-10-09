package com.hq.chainStore.service.buser.apiData;

import java.util.HashSet;
import java.util.Set;

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

	public static BUserAddApiForm newInstance() {
		return new BUserAddApiForm();
	}

	public String getName() {
		return name;
	}

	public BUserAddApiForm setName(String name) {
		this.name = name;
		return this;
	}

	public long getPhone() {
		return phone;
	}

	public BUserAddApiForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public BUserAddApiForm setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public BUserAddApiForm setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public int getGender() {
		return gender;
	}

	public BUserAddApiForm setGender(int gender) {
		this.gender = gender;
		return this;
	}

	public int getAge() {
		return age;
	}

	public BUserAddApiForm setAge(int age) {
		this.age = age;
		return this;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public BUserAddApiForm setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
		return this;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public BUserAddApiForm setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
		return this;
	}

	public String getActivateCode() {
		return activateCode;
	}

	public BUserAddApiForm setActivateCode(String activateCode) {
		this.activateCode = activateCode;
		return this;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public BUserAddApiForm setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		return this;
	}
}
