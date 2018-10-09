package com.hq.testClass.robot.schedule;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.schedule.apidata.ScheduleQueryParams;
import com.hq.chainStore.service.schedule.data.Schedule;

public class ScheduleRobot {

	private ScheduleRobotData data;
	
	public static ScheduleRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}
	
	public static ScheduleRobot newInstance(int mark){
		ScheduleRobot robot = new ScheduleRobot();
		robot.data = ScheduleRobotData.newInstance(mark);
		return robot;
	}
	
	public Schedule getSchedule(long beauticianId){
		return ScheduleRobotHelper.getInstance().getSchedule(this, beauticianId);
	}
	
	public void updateStatus(Schedule schedule){
		ScheduleRobotHelper.getInstance().updateStatus(this, schedule);
	}
	
	public List<Schedule> findScheduleList(ScheduleQueryParams params){
		return ScheduleRobotHelper.getInstance().findScheduleList(this, params);
	}

	public ScheduleRobotData getData() {
		return data;
	}

	public void setData(ScheduleRobotData data) {
		this.data = data;
	}
	
	
	
}
