package com.hq.storeManagerRestClient.testClass.robot.muser;

public class Admin {
	private MUserRobot robot;
	
	public static Admin newAdmin(MUserRobot robot){
		Admin boss = new Admin();
		boss.robot = robot;
		return boss;
	}

	public MUserRobot getRobot() {
		return robot;
	}

	public void setRobot(MUserRobot robot) {
		this.robot = robot;
	}

	public boolean loginWithParam(String account, String password){
		return robot.loginWithParam(account, password);
	}

	public long getId() {
		return this.robot.getId();
	}
}
