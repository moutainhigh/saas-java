package com.hq.chainStore.service.detailDataVersion.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

public class DetailDataVersionMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		DetailDataVersion data = DetailDataVersionSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(JsonUtil.getInstance().toJson(data));
		DetailDataVersion data2 = DetailDataVersionSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(JsonUtil.getInstance().toJson(data2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
