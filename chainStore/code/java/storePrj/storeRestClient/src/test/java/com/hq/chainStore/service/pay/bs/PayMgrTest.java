package com.hq.chainStore.service.pay.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.pay.apiData.BeScanApiForm;
import com.hq.chainStore.service.pay.apiData.ScanApiForm;
import com.hq.chainStore.service.pay.data.PayResp;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class PayMgrTest {

	private static Boss boss;
	private static long storeId=15L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13165632970L);
		boss.getRobot().getData().setPassword("666666");
		boss.login();
		storeId = boss.getRobotStoreId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testBeScan() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long orderId = 1866L;
		BeScanApiForm beScanApiForm = BeScanApiForm.newInstance();
		beScanApiForm.setApiType(0).setStoreId(storeId)
		.setOrderId(orderId).setTotalAmount("0.01");
		PayResp payResp = PayMgr.getInstance().beScan(beScanApiForm);
		System.out.println(JsonUtil.getInstance().toJson(payResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testScan() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long orderId = 1866L;
		ScanApiForm scanApiForm = ScanApiForm.newInstance();
		scanApiForm.setApiType(0).setStoreId(storeId)
		.setOrderId(orderId).setTotalAmount("0.01")
		.setAuthCode("123456789");
		PayResp payResp = PayMgr.getInstance().scan(scanApiForm);
		System.out.println(JsonUtil.getInstance().toJson(payResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
