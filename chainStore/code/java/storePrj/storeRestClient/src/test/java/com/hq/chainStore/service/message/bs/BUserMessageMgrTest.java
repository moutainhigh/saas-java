package com.hq.chainStore.service.message.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.message.apiData.MessageQueryForm;
import com.hq.chainStore.service.message.data.BUserMessage;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;


public class BUserMessageMgrTest {
	private static Boss boss;
	private static long storeId=0L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13800096619L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
//		storeId = boss.getRobotStoreId();
		storeId = 16052L;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testPage() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MessageQueryForm queryForm = MessageQueryForm.newInstance();
		queryForm.setMinTime(0);
		PageResp<BUserMessage> page = BUserMessageMgr.getInstance().findMessagePage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
