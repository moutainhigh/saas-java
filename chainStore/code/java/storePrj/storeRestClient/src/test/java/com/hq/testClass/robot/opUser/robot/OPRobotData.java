package com.hq.testClass.robot.opUser.robot;

public class OPRobotData {

	//随机数标记，用来生成电话号码等信息
	private int mark;
	
	private long userId;
	
	private long phone;
	
	private String name;
	
	private String password;
	
	private String token;
	

	
	public static OPRobotData newInstance(int mark){
		OPRobotData data = new OPRobotData();
		data.mark = mark;
		data.phone = 13800000000L+mark;
		data.name = "opuser_"+mark;
		data.password = "123456";
		
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
	
	
	


}