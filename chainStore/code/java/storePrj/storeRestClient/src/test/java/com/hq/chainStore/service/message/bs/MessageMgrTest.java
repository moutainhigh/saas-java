package com.hq.chainStore.service.message.bs;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.message.data.Message;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.message.MessageRobot;


public class MessageMgrTest {
	private static Boss boss;
	private static long storeId=0L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testMessage() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MessageRobot robot = MessageRobot.newRandom();
		List<Message> list = robot.findMessageList(storeId, boss.getId());
		System.out.println(list);
		Assert.assertFalse("应该不为空", list.isEmpty());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
