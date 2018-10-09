package com.hq.testClass.robot.sms;

import com.hq.chainStore.service.sms.apiData.SmsAPIForm;
import com.hq.chainStore.service.sms.apiData.SmsResp;
import com.hq.chainStore.service.sms.bs.SmsMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class SmsRobotHelper {

	private static SmsRobotHelper instance = new SmsRobotHelper();

	public static SmsRobotHelper getInstance() {
		return instance;
	}

	public SmsResp sendSms(SmsRobot robot) {
		SmsRobotData data = robot.getData();
		SmsAPIForm apiForm = SmsAPIForm.newInstance();
		FastBeanCopyer.getInstance().copy(data, apiForm);
		return SmsMgr.getInstance().sendSms(apiForm);
	}

}