package com.hq.chainStore.service.schedule.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.schedule.apidata.ScheduleQueryParams;
import com.hq.chainStore.service.schedule.data.Schedule;
import com.hq.chainStore.service.schedule.data.ScheduleStatusEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.schedule.ScheduleRobot;

public class ScheduleMgrTest {

	private static long storeId = 21L;
	private static Boss boss;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
	}
	
	

	@Test
	public void testFindList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ScheduleRobot robot = ScheduleRobot.newRandom();
		ScheduleQueryParams params = ScheduleQueryParams.newInstance();

		params.setStoreId(storeId);
		params.setBeauticianId(53L);
		params.setStatu(1);
		
		List<Schedule> list = robot.findScheduleList(params);

		AccessTokenMgr.getInstance().removeOpIdTL();
		System.out.println("***************************************************************************");
		System.out.println(list);
		
	}
	
	@Test
	public void testUpdateStatu() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ScheduleRobot robot = ScheduleRobot.newRandom();
		
		Long scheduleId =78L;
		Schedule schedule = robot.getSchedule(scheduleId);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(schedule);
		
		//更改状态
		schedule.setStatu(ScheduleStatusEnum.FINISH.ordinal());
		robot.updateStatus(schedule);
		
		Schedule schedule2 = robot.getSchedule(scheduleId);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(schedule2);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
//		Assert.assertFalse("处理预约后，状态不一样", schedule.getStatu() != schedule2.getStatu());
	}
	

}
