package com.hq.chainStore.service.arrearage.bs;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.arrearage.apiData.ArrearageAddPaymentHistoryApiForm;
import com.hq.chainStore.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.chainStore.service.arrearage.data.Arrearage;
import com.hq.chainStore.service.arrearage.data.ArrearageGroup;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.order.data.PayItem;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;


public class ArrearageMgrTest {
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
	}
	
//	@Ignore
	@Test
	public void testAddPaymentHistory() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long arrearageId = 70L;
		ArrearageAddPaymentHistoryApiForm addPaymentHistoryApiForm = ArrearageAddPaymentHistoryApiForm.newInstance();
		addPaymentHistoryApiForm.addPayItmes(PayItem.newInstance(RandomUtils.nextInt(0, 4), 776f));
		ArrearageMgr.getInstance().addPaymentHistory(arrearageId, storeId, addPaymentHistoryApiForm);
		Arrearage arrearage = ArrearageMgr.getInstance().getArrearage(storeId, arrearageId);
		System.out.println(JsonUtil.getInstance().toJson(arrearage));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long arrearageId = 77L;
		Arrearage arrearage = ArrearageMgr.getInstance().getArrearage(storeId, arrearageId);
		System.out.println(JsonUtil.getInstance().toJson(arrearage));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindArrearageList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<Arrearage> list = ArrearageMgr.getInstance().findArrearageList(buildQueryForm());
		for (Arrearage arrearage : list) {
			System.out.println(JsonUtil.getInstance().toJson(arrearage));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindArrearageGroupList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<ArrearageGroup> list = ArrearageMgr.getInstance().findArrearageGroupList(buildQueryForm());
		System.out.println(JsonUtil.getInstance().toJson(list));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetArrearagePageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PageResp<Arrearage> pageInfo = ArrearageMgr.getInstance().getArrearagePageInfo(buildQueryForm());
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetArrearageGroupPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PageResp<ArrearageGroup> pageInfo = ArrearageMgr.getInstance().getArrearageGroupPageInfo(buildQueryForm());
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private ArrearageQueryForm buildQueryForm(){
		long now = System.currentTimeMillis();
		long minTime = now - 100L * 24 * 3600 * 1000;
		long maxTime = now + 3600*1000;
		
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setStoreId(storeId)
			//.setLeaguerId("21_66814")
			.setMinTime(minTime).setMaxTime(maxTime);
		return queryForm;
	}
}
