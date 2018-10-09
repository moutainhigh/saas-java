package com.hq.chainStore.service.chainCard.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.chainCard.data.ChainCard;
import com.hq.chainStore.service.chainCard.data.MembershipCardDetail;
import com.hq.chainStore.service.chainCard.data.ProductCardDetail;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChainCardMgrTest {
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
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainCard data = ChainCardMgr.getInstance().get(chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindMemberCardDetail() {
		long chainId=1L;
		String id="_cmci_1_2";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MembershipCardDetail data = ChainCardMgr.getInstance().findMemberCardDetail(id, chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindProductCardDetail() {
		long chainId=1L;
		String id="_cpci_1_1";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardDetail data = ChainCardMgr.getInstance().findProductCardDetail(id, chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
