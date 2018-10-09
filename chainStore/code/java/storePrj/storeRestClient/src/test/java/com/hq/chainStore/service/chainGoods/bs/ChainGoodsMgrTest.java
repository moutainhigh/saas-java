package com.hq.chainStore.service.chainGoods.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.chainGoods.data.GoodsDetail;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChainGoodsMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testGet() {
		long chainId=1L;
		String id="_cgti_1_6";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetail data = ChainGoodsMgr.getInstance().findGoodsDetail(id, chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
