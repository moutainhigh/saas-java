package com.hq.chainStore.service.leaguerAffair.bs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.leaguerAffair.data.LeaguerAffair;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.leaguerAffair.LeaguerAffairRobot;

public class LeaguerAffairMgrTest {

	private static Boss boss;
	private static long storeId=21L;
	private static String leaguerAffairId = "21_158";
	
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
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerAffairRobot robot = LeaguerAffairRobot.newRandom();
		LeaguerAffair data = robot.getLeaguerAffair(leaguerAffairId);
		System.out.println(data);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("应该非空", data);
	}
	
	@Test
	public void testAdd() {
		ProductCard productCard = boss.getRandomProductCard(storeId);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerAffairRobot robot = LeaguerAffairRobot.newRandom();
		LeaguerAffair data = robot.getLeaguerAffair(leaguerAffairId);
		System.out.println(data);
		
//		robot.addDiscountCard(storeId, leaguerAffairId, discountCard.getId());
//		robot.addMembershipCard(storeId, leaguerAffairId, membershipCard.getId());
		robot.addProductCard(storeId, leaguerAffairId, productCard.getId());
//		robot.addDiscountCard(storeId, leaguerAffairId, "_dis_21_1");
//		robot.addMembershipCard(storeId, leaguerAffairId, "_mem_21_1");
//		robot.addAlarmClock(storeId, leaguerAffairId, data.getAlarmClockIndex() + 1);
//		robot.addArchives(storeId, leaguerAffairId, data.getArchivesIndex() + 1);
		
		data = robot.getLeaguerAffair(leaguerAffairId);
		System.out.println(data);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerAffairRobot robot = LeaguerAffairRobot.newRandom();
		LeaguerAffair data = robot.getLeaguerAffair(leaguerAffairId);
		System.out.println(data);
		
//		robot.delDiscountCard(storeId, leaguerAffairId, "_dis_21_1");
//		robot.delMembershipCard(storeId, leaguerAffairId, "_mem_21_1");
		robot.delProductCard(storeId, leaguerAffairId, "_prd_21_4");
//		robot.delAlarmClock(storeId, leaguerAffairId, 1);
//		robot.delArchives(storeId, leaguerAffairId, 2);
		
		data = robot.getLeaguerAffair(leaguerAffairId);
		System.out.println(data);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
