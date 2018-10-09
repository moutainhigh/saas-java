package com.hq.testClass.robot.logger;

import org.apache.commons.lang3.RandomUtils;

public class LoggerRobot {
	
	private LoggerRobotData data;
	
	public static LoggerRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static LoggerRobot newInstance(int mark){
		LoggerRobot robot = new LoggerRobot();
		robot.data = LoggerRobotData.newInstance(mark);
		return robot;
	}
	
	public void saveLogger(){
		LoggerRobotHelper.getInstance().saveLogger(this);
	}
	
	public LoggerRobotData getData() {
		return data;
	}

	public void setData(LoggerRobotData data) {
		this.data = data;
	}	
}
