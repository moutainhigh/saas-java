package com.hq.testClass.robot.BUserDevice;

import org.apache.commons.lang3.RandomUtils;

/** 
 * @ClassName: BUserDeviceRobot 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月29日 上午11:07:49 
 *  
 */
public class BUserDeviceRobot {

	private BUserDeviceRobotData data;
	
	public static BUserDeviceRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static BUserDeviceRobot newInstance(int mark){
		BUserDeviceRobot robot = new BUserDeviceRobot();
		robot.data = BUserDeviceRobotData.newInstance(mark);
		return robot;
	}

	public BUserDeviceRobotData getData() {
		return data;
	}

	public void setData(BUserDeviceRobotData data) {
		this.data = data;
	}
	
	
}
