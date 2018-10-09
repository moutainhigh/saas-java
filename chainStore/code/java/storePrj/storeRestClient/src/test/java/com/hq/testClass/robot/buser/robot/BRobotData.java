package com.hq.testClass.robot.buser.robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BRobotData {
	// 用户关联的店铺id列表
	private List<Long> storeIds = new ArrayList<Long>();

	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private long userId;

	private long phone;

	private String name;

	private String password;

	private String token;

	private String verifyCode;// 验证码
	
	private Set<Integer> roleSet = new HashSet<Integer>();//角色

	public static BRobotData newInstance(int mark) {
		BRobotData data = new BRobotData();
		data.mark = mark;
		data.phone = 13800000000L + mark;
		data.name = "医美莱" + mark;
		data.password = "123456";
		data.roleSet.add(0);

		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public List<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

}