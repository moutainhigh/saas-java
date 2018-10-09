package com.hq.chainStore.service.storeVip.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class StoreVipMgrTest {
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}

	@Test
	public void testCheckExpired() {
		//老板注册、登陆、开店、提交审核
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();
		BUser buser = boss.getBuser(boss.getId());
		System.out.println("buser : "+JsonUtil.getInstance().toJson(buser));
		

//		Store store = boss.openSimpleStore(1);
//
//		boss.login();
//
//		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
	}

}
