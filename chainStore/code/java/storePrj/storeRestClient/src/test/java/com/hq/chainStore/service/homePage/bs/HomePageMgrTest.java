package com.hq.chainStore.service.homePage.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.homePage.apiData.QueryHomePageForm;
import com.hq.chainStore.service.homePage.data.HomePage;
import com.hq.chainStore.service.homePage.data.HomePageItemEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class HomePageMgrTest {
	private static Boss boss;
	private static long storeId=0;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testHomePage(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		QueryHomePageForm queryForm = QueryHomePageForm.newInstance();
		queryForm.setBuserId(boss.getId());
		queryForm.setStoreId(storeId);
		Set<Integer> items = new HashSet<Integer>();
//		items.add(HomePageItemEnum.Card.ordinal());
		items.add(HomePageItemEnum.StatisticsData.ordinal());
		queryForm.setItems(items);
		HomePage homePage = HomePageMgr.getInstance().getHomePageData(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(homePage));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
