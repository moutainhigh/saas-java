package com.hq.chainStore.service.bonus.bs;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.bonus.apiData.BonusRecordForm;
import com.hq.chainStore.service.bonus.apiData.BonusRecordQueryForm;
import com.hq.chainStore.service.bonus.data.BonusRecord;
import com.hq.chainStore.service.bonus.data.BonusRecordDataHelper;
import com.hq.chainStore.service.bonus.data.BonusRecordGroup;
import com.hq.chainStore.service.bonus.data.MonthBonus;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.order.data.BuyTypeEnum;
import com.hq.chainStore.service.workFlowData.data.PrdCardPayEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestConstants;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.bonus.BonusRobot;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class BonusRecordMgrTest {
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
	public void testT(){
	}
	
	@Test
	public void testSave() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		BonusRecordForm inputForm = BonusRecordForm.newInstance();
		inputForm.setStoreId(storeId);
		inputForm.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
		inputForm.setOrderId(1015L);
		inputForm.setPgId("13");
		inputForm.setPrdCardPayType(PrdCardPayEnum.CashPay.ordinal());
		inputForm.setProductCardId("");
		inputForm.setUserBonusMap(BonusRobot.getInstance().buildUserBonusMap());
		BonusRecordMgr.getInstance().saveBonusRecord(inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		BonusRecord bonusRecord = BonusRecordMgr.getInstance().getBonusRecord(22L);
		System.out.println(JsonUtil.getInstance().toJson(bonusRecord));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		long minTime = now - 100L * 24 * 3600 * 1000;
		long maxTime = now + 3600 * 1000;
		BonusRecordQueryForm params = BonusRecordQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setPageItemCount(5);
		params.setPageNo(1);
		params.setStatus("1");
		
		List<BonusRecord> list = BonusRecordMgr.getInstance().findBonusRecordList(params);
		System.out.println(list.size());
		System.out.println(JsonUtil.getInstance().toJson(list));
		//DateUtils4Client.convertStringToDate("yyyy-MM-dd", "2017-11-10")
		Map<Long, MonthBonus> storeBuserMonthBonusMap = BonusRecordDataHelper.getInstance().getStoreBuserMonthBonusMap(list, new Date(minTime));
		System.out.println(JsonUtil.getInstance().toJson(storeBuserMonthBonusMap));
		Map<String, MonthBonus> buserMonthBonusMap = BonusRecordDataHelper.getInstance().getBuserMonthBonusMap(list, 35L);
		System.out.println(JsonUtil.getInstance().toJson(buserMonthBonusMap));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindBonusRecordPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		long minTime = now - 100L * 24 * 3600 * 1000;
		long maxTime = now + 3600 * 1000;
		BonusRecordQueryForm params = BonusRecordQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setBuserName("考");
		params.setPageItemCount(5);
		params.setPageNo(1);
//		params.setStatus("0");
		
		PageResp<BonusRecord> result = BonusRecordMgr.getInstance().findBonusRecordPageInfo(params);
		System.out.println(JsonUtil.getInstance().toJson(result));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindBonusRecordGroupPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		long minTime = now - TestConstants.ONE_MONTH * 6;
		long maxTime = now + TestConstants.ONE_DAY;
		BonusRecordQueryForm params = BonusRecordQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setPageItemCount(14);
		params.setPageNo(1);
		
		PageResp<BonusRecordGroup> result = BonusRecordMgr.getInstance().findBonusRecordGroupPageInfo(params);
		System.out.println(JsonUtil.getInstance().toJson(result));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
