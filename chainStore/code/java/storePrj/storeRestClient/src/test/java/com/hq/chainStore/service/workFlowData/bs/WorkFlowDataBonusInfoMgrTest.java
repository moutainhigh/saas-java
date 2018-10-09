package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.bonus.data.BonusTypeEnum;
import com.hq.chainStore.service.order.data.BuyTypeEnum;
import com.hq.chainStore.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.chainStore.service.workFlowData.apiData.BonusInfoUpdateForm;
import com.hq.chainStore.service.workFlowData.data.PrdCardPayEnum;
import com.hq.chainStore.service.workFlowData.data.UserBonus;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataBonusInfoMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	private static long workFlowDataId = 5L;
	private static String bonusId = "0_1_81";
	
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
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();
		UserBonus bonus=UserBonus.newInstance();
		bonus.setAmount(RandomUtils.nextFloat(1000f, 2000f));
		bonus.setBonusType(BonusTypeEnum.FixedBonus.ordinal());
		bonus.setBuserId(RandomUtils.nextLong(1L, 100L));
		bonus.setCost(RandomUtils.nextFloat(1000f, bonus.getAmount()));
		bonus.setPercentage(RandomUtils.nextFloat(0.1f, 0.2f));
		userBonusMap.put(bonus.getBuserId(), bonus);
		
		BonusInfoAddForm addForm = BonusInfoAddForm.newInstance();
		addForm.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
//		addForm.setPrdCardPayType(PrdCardPayEnum.CashPay.ordinal());
		addForm.setPrdCardPayType(PrdCardPayEnum.PrdCard.ordinal());
		addForm.setPgId(RandomUtils.nextLong(1L, 100L)+"");
		addForm.setUserBonusMap(userBonusMap);
		
		WorkFlowData record = WorkFlowDataBonusInfoMgr.getInstance().addBonusInfo(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		WorkFlowDataBonusInfoMgr.getInstance().deleteBonusInfo(workFlowDataId, bonusId);
		List<String> bonusIds = new ArrayList<String>();
		bonusIds.add("0_1_1_");
		bonusIds.add("0_1_2_");
		WorkFlowDataBonusInfoMgr.getInstance().deleteBonusInfo(4514L, bonusIds);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();
		UserBonus bonus=UserBonus.newInstance();
		bonus.setAmount(RandomUtils.nextFloat(1000f, 2000f));
		bonus.setBonusType(BonusTypeEnum.FixedBonus.ordinal());
		bonus.setBuserId(RandomUtils.nextLong(1L, 100L));
		bonus.setCost(RandomUtils.nextFloat(1000f, bonus.getAmount()));
		bonus.setPercentage(RandomUtils.nextFloat(0.1f, 0.2f));
		userBonusMap.put(bonus.getBuserId(), bonus);
		
		BonusInfoUpdateForm updateForm = BonusInfoUpdateForm.newInstance();
		updateForm.setBonusId(bonusId);
		updateForm.setUserBonusMap(userBonusMap);
		WorkFlowData data = WorkFlowDataBonusInfoMgr.getInstance().updateBonusInfo(workFlowDataId, bonusId, updateForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
