package com.hq.chainStore.service.buserRole.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buserRole.data.BuserRole;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class BuserRoleClientMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		BuserRole buserRole = BuserRoleClientMgr.getInstance().get(boss.getId());
		System.out.println(JsonUtil.getInstance().toJson(buserRole));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
