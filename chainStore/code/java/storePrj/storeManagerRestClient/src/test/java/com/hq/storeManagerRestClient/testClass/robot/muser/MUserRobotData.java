package com.hq.storeManagerRestClient.testClass.robot.muser;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class MUserRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;
	//用户ID
	private long muserId;
	// 账号
	private String account;
	// 名称
	private String name;
	// 密码
	private String password;
	// 头像
	private String headImg;
	// 性别
	private int gender;

	public static MUserRobotData newInstance(int mark) {
		MUserRobotData data = new MUserRobotData();
		data.mark = mark;
		data.account = RandomStringUtils.random(5, "asdfghjkl");
		data.name = "测试-" + mark;
		data.password = "123456";
		data.gender = RandomUtils.nextInt(0, 2);
		data.headImg = "test.png";
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public long getMuserId() {
		return muserId;
	}

	public void setMuserId(long muserId) {
		this.muserId = muserId;
	}

}