package com.hq.chainStore.service.storeIncomePay.bs;

import com.hq.chainStore.service.incomePay.data.IncomePayCategoryEnum;
import com.hq.chainStore.service.storeIncomePay.apiData.IncomePayTypeAddForm;
import com.hq.chainStore.service.storeIncomePay.apiData.IncomePayTypeRemoveForm;
import com.hq.chainStore.service.storeIncomePay.apiData.IncomePayTypeUpdateForm;
import com.hq.chainStore.service.storeIncomePay.data.IncomePayType;
import com.hq.chainStore.service.storeIncomePay.data.StoreIncomePay;
import com.hq.chainStore.service.storeIncomePay.data.StoreIncomePaySynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StoreIncomePayMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreIncomePay storeIncomePay = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		System.out.println(JsonUtil.getInstance().toJson(storeIncomePay));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/********************************** 收支分类操作 **********************************/
	/**
	 * 测试添加分类
	 */
	@Test
	public void testAddIncomePayType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreIncomePay storeIncomePay = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		System.out.println("---->"+JsonUtil.getInstance().toJson(storeIncomePay));
		IncomePayTypeAddForm dataForm = IncomePayTypeAddForm.newInstance();
		dataForm.setIndex(storeIncomePay.getIncomePayTypeIdIndex() + 1);
		dataForm.setName("测试分类带类别random4");
		dataForm.setCategory(IncomePayCategoryEnum.INCOME.ordinal());
		StoreIncomePayMgr.getInstance().addIncomePayType(storeId, dataForm);
		storeIncomePay = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		System.out.println("---->"+JsonUtil.getInstance().toJson(storeIncomePay));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/**
	 * 测试删除收支分类
	 */
	@Test
	public void testRemoveIncomePayType() {
		IncomePayType incomePayType = getRandomIncomePayType(storeId);// 根据 店铺id 获取 随机的 收支分类
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreIncomePay storeIncomePay = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		System.out.println(JsonUtil.getInstance().toJson(storeIncomePay));
		IncomePayTypeRemoveForm dataForm = IncomePayTypeRemoveForm.newInstance();
		dataForm.setIncomePayTypeId(incomePayType.getId());
		System.out.println(incomePayType.getId());
		StoreIncomePayMgr.getInstance().removeIncomePayType(storeId, dataForm);
		StoreIncomePay storeIncomePay2 = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		System.out.println(JsonUtil.getInstance().toJson(storeIncomePay2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/**
	 * 根据 店铺id 获取 随机的 收支分类
	 *
	 * @param storeId
	 * @return
	 */
	private IncomePayType getRandomIncomePayType(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreIncomePay storeIncomePay = getStoreIncomePay(storeId);// 获取 店铺收支 StoreIncomePay
		Collection<IncomePayType> values = storeIncomePay.getIncomePayTypeMap().values();
		List<IncomePayType> types = new ArrayList<>(values);
		IncomePayType incomePayType = types.get(RandomUtils.nextInt(1, types.size()));
		return incomePayType;
	}

	/**
	 * 获取 店铺收支 StoreIncomePay
	 * 
	 * @param storeId
	 * @return
	 */
	private StoreIncomePay getStoreIncomePay(long storeId) {
		return StoreIncomePaySynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
	}

	@Test
	public void testUpdIncomePayType() {
		IncomePayType incomePayType = getRandomIncomePayType(storeId);//// 根据 店铺id 获取 随机的 收支分类
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		IncomePayTypeUpdateForm dataForm = IncomePayTypeUpdateForm.newInstance();
		dataForm.setId(incomePayType.getId());
		System.out.println(incomePayType.getId());
		dataForm.setName("测试修改分类");
		StoreIncomePayMgr.getInstance().updateIncomePayType(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
