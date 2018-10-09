package com.hq.testClass.robot.schedule;

import java.util.List;

import com.hq.chainStore.service.schedule.apidata.ScheduleQueryParams;
import com.hq.chainStore.service.schedule.apidata.ScheduleUpdateStatusForm;
import com.hq.chainStore.service.schedule.bs.ScheduleMgr;
import com.hq.chainStore.service.schedule.data.Schedule;



public class ScheduleRobotHelper {

	private static ScheduleRobotHelper instance = new ScheduleRobotHelper();
	
	public static ScheduleRobotHelper getInstance(){
		return instance;
	}
	
	public List<Schedule> findScheduleList(ScheduleRobot robot, ScheduleQueryParams params) {
		return ScheduleMgr.getInstance().findScheduleList(params);
	}
	
	public void updateStatus(ScheduleRobot robot, Schedule schedule) {
		ScheduleUpdateStatusForm updateStatus = ScheduleUpdateStatusForm.newInstance();
		updateStatus.setStatu(schedule.getStatu());
		ScheduleMgr.getInstance().updateScheduleStatus(schedule.getId(), schedule.getStoreId(), updateStatus);
	}

	public Schedule getSchedule(ScheduleRobot scheduleRobot, long scheduleId) {
		return ScheduleMgr.getInstance().getSchedule(scheduleId);
		
	}
	
	
	
}
