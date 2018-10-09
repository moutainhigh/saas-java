package com.hq.testClass.robot.sms;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.sms.apiData.SmsResp;

public class SmsRobot {

	private SmsRobotData data;

	public static SmsRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static SmsRobot newInstance(int mark) {
		SmsRobot robot = new SmsRobot();
		robot.data = SmsRobotData.newInstance(mark);
		return robot;
	}

	public SmsResp sendSms() {
		return SmsRobotHelper.getInstance().sendSms(this);
	}

	public SmsRobotData getData() {
		return data;
	}

	public void setData(SmsRobotData data) {
		this.data = data;
	}
}
