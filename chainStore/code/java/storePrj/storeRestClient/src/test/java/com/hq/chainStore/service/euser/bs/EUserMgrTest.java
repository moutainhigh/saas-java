package com.hq.chainStore.service.euser.bs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.euser.data.EUser;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.euser.robot.EUserRobot;

public class EUserMgrTest {
	
	private static Boss boss;


	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(12100000000L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testAdd() {
		EUserRobot robot = EUserRobot.newRandom();
		EUser euser = robot.add();
		if(euser!=null){
			System.out.println(euser.toString());

		}
	}

	
	@Test
	public void testGet() {
		EUserRobot robotTmp = EUserRobot.newRandom();
		EUser euser = robotTmp.getByEUserId(2);
		System.out.println("euser  "+euser.toString());
		assertTrue("euser 应该有一条数据", euser.getName()!=null);
		
	}
	
	@Test
	public void testUpdate() {
		EUserRobot robotTmp = EUserRobot.newRandom();
		EUser euser1 = robotTmp.getByEUserId(1);
		System.out.println("euser1  "+euser1.toString());
		robotTmp.updateCount(euser1);
		EUser euser2 = robotTmp.getByEUserId(1);
		System.out.println("euser2  "+euser2.toString());
	}
	
	
	@Test
	public void testGetList() {
		EUserRobot robot = EUserRobot.newRandom();
		Integer pageItemCount =10;
		Integer pageNo = 1;
		List<EUser> eusers = robot.getList(pageItemCount,pageNo);
		System.out.println(eusers.size()+" lalala");
	}

}
