package com.hq.testClass.robot.logger;

import com.hq.chainStore.service.logger.apiData.LoggerAddApiForm;
import com.hq.chainStore.service.logger.bs.LoggerMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;


public class LoggerRobotHelper {

	private static LoggerRobotHelper instance = new LoggerRobotHelper();

	public static LoggerRobotHelper getInstance() {
		return instance;
	}

	public void saveLogger(LoggerRobot robot) {
		LoggerAddApiForm addForm = LoggerAddApiForm.newInstance();
		LoggerRobotData data = robot.getData();
		FastBeanCopyer.getInstance().copy(data, addForm);
		LoggerMgr.getInstance().saveLogger(addForm);
	}
	
}