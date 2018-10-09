package com.hq.chainStore.service.orderComment.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.orderComment.apiData.OrderCommentQueryParams;
import com.hq.chainStore.service.orderComment.data.OrderComment;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.orderComment.OrderCommentRobot;


public class OrderCommentMgrTest {
	private static Boss boss;
	private static long storeId=21L;

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
	public void testAll(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		OrderComment orderComment = OrderCommentMgr.getInstance().getOrderComment(34L);
//		System.out.println(orderComment);
		
		OrderCommentQueryParams params = OrderCommentQueryParams.newInstance();
		params.setBeauticianId(1L);
		List<OrderComment> orderComments = OrderCommentMgr.getInstance().findOrderCommentList(params);
		System.out.println(orderComments);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testSaveBeauticianComment(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		OrderCommentRobot robot = OrderCommentRobot.newRandom();
		robot.saveBeauticianComment(386L, storeId);
		OrderComment orderComment = OrderCommentMgr.getInstance().getOrderComment(386L);
		System.out.println(orderComment);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
