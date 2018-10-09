
package com.hq.testClass.robot.opUser.robot;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.opuser.data.OPUser;

public class OPRobot {

	private OPRobotData data;

	public static OPRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 100000);
		return newInstance(mark);
	}

	public static OPRobot newInstance(int mark) {

		OPRobot robot = new OPRobot();
		robot.data = OPRobotData.newInstance(mark);
		return robot;

	}

	public boolean reg() {
		return OPRobotHelper.getInstance().reg(this);
	}

	public boolean login() {
		return OPRobotHelper.getInstance().login(this);
	}

	public OPUser get(Long opId){
		return OPRobotHelper.getInstance().getOpUser(this, opId);
	}
	
	public OPUser get(){
		long opId = this.data.getUserId();
		return OPRobotHelper.getInstance().getOpUser(this, opId);
	}
	
	public OPRobotData getData() {
		return data;
	}

	public void setData(OPRobotData data) {
		this.data = data;
	}

	public long getId() {
		return this.data.getUserId();
	}

}