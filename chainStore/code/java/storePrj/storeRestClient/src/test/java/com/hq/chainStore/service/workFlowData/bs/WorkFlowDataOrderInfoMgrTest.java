package com.hq.chainStore.service.workFlowData.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.OrderInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataOrderInfoMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	private static long workFlowDataId = 5L;
	private static long orderId = 2;
	
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
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		OrderInfoForm addForm = OrderInfoForm.newInstance();
		addForm.setOrderId(orderId);
		WorkFlowData record = WorkFlowDataOrderInfoMgr.getInstance().addOrderInfo(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
