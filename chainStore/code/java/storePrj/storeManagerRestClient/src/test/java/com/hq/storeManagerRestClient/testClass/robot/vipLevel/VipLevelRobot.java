package com.hq.storeManagerRestClient.testClass.robot.vipLevel;

import org.apache.commons.lang3.RandomUtils;

import com.hq.storeManagerRestClient.service.vipLevel.apiData.AddVipLevelForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class VipLevelRobot {

	private VipLevelRobotData robotData;
	
	public static VipLevelRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 100000);
		return newInstance(mark);
	}

	public static VipLevelRobot newInstance(int mark) {
		VipLevelRobot robot = new VipLevelRobot();
		robot.robotData = VipLevelRobotData.newInstance(mark);
		return robot;
	}
	
	public AddVipLevelForm toAddForm() {
		AddVipLevelForm addForm = AddVipLevelForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.robotData, addForm);
		return addForm;
	}
	

	public VipLevelRobotData getRobotData() {
		return robotData;
	}

	public void setRobotData(VipLevelRobotData robotData) {
		this.robotData = robotData;
	}
	
}
