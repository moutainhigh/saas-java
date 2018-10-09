
package com.hq.testClass.robot.buser.robot;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.buser.data.BUser;

public class BRobot {

	private BRobotData data;

	public static BRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 100000);
		return newInstance(mark);
	}

	public static BRobot newInstance(int mark) {

		BRobot robot = new BRobot();
		robot.data = BRobotData.newInstance(mark);
		return robot;

	}

	public boolean reg() {
		return BRobotHelper.getInstance().reg(this);
	}
	
	public boolean regWithParam(long phone) {
		return BRobotHelper.getInstance().regWithParam(this,phone);
	}
	

	public boolean login() {
		return BRobotHelper.getInstance().login(this);
	}
	
	public boolean loginWithParam(long phone,long buserId) {
		return BRobotHelper.getInstance().loginWithParam(this,phone,buserId);
	}

	public BUser getBuser(Long buserId) {
		return BRobotHelper.getInstance().getBuser(this, buserId);
	}
	
	public BUser findByPhone() {
		return BRobotHelper.getInstance().findByPhone(this);
	}
	
	public List<BUser> findByMultitId(String idList) {
		return BRobotHelper.getInstance().findByMultitId(this,idList);
	}
	
	public BUser getBuserFromHodler() {
		return BRobotHelper.getInstance().getBuserFromHodler(this);
	}
	
	
	public boolean updateInfo() {
		return BRobotHelper.getInstance().updateInfo(this);
	}
	
	public boolean changePassword() {
		return BRobotHelper.getInstance().changePassword(this);
	}
	
	public boolean resetPassword() {
		return BRobotHelper.getInstance().resetPassword(this);
	}

	public BRobotData getData() {
		return data;
	}

	public void setData(BRobotData data) {
		this.data = data;
	}

	public long getId() {
		return this.data.getUserId();
	}
	
	public void changeVipLevel(){
		BRobotHelper.getInstance().changeVipLevel(this);
	}

}