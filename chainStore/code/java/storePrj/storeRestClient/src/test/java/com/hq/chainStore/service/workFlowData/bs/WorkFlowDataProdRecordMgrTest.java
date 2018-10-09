package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdInfoForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataProdRecordMgrTest {
	private static Boss boss;
	private static long storeId = 0L;


	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
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
	public void testAdd() {
		long workFlowDataId = 7689L;
		String productId = boss.getRandomProductInfo(storeId).getId();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
		inputForm.setCount(RandomUtils.nextInt(0, 10));
		inputForm.setOldPrice(RandomUtils.nextFloat(100f, 1000f));
		inputForm.setDiscount(1.0f);
		inputForm.setProdId(productId+"");
		WorkFlowData record = WorkFlowDataProdRecordMgr.getInstance().addProdRecord(workFlowDataId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testAddList() {
		long workFlowDataId = 7689L;
		String productId = boss.getRandomProductInfo(storeId).getId();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<ProdRecordAddForm> inputForms = new ArrayList<ProdRecordAddForm>();
		ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
		inputForm.setCount(RandomUtils.nextInt(0, 10));
		inputForm.setPrice(RandomUtils.nextFloat(100f, 1000f));
		inputForm.setProdId(productId+"");
		inputForms.add(inputForm);
		ProdRecordAddListForm addListForm = ProdRecordAddListForm.newInstance();
		addListForm.setProdRecordAddForms(inputForms);
		WorkFlowData record = WorkFlowDataProdRecordMgr.getInstance().addProdRecordList(workFlowDataId, addListForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testUpdList() {
		long workFlowDataId = 7689L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProdRecordUpdListForm updListForm = ProdRecordUpdListForm.newInstance();
		// List<ProdRecordAddForm> inputForms = new ArrayList<ProdRecordAddForm>();
		// ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
		// inputForm.setCount(RandomUtils.nextInt(0, 10));
		// inputForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		// inputForm.setProdId("13");
		// inputForms.add(inputForm);
		// updListForm.setProdRecordAddForms(inputForms);

		// workFlowData.getProdRecordMap().remove("12");
		//
		// Collection<ProdRecord> values = workFlowData.getProdRecordMap().values();
		// for (ProdRecord prd : values) {
		// ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
		// FastBeanCopyer.getInstance().copy(prd, inputForm);
		// inputForms.add(inputForm);
		// updListForm.setProdRecordAddForms(inputForms);
		// }
		WorkFlowData record = WorkFlowDataProdRecordMgr.getInstance().updProdRecordList(workFlowDataId, updListForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testDelete() {
		long workFlowDataId = 7689L;
		String productId = "2";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowData data = WorkFlowDataProdRecordMgr.getInstance().deleteProdRecord(workFlowDataId, productId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testUpdateInfo() {
		long workFlowDataId = 7689L;
		String productId = boss.getRandomProductInfo(storeId).getId();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());

		ProdRecordUpdInfoForm updInfoForm = ProdRecordUpdInfoForm.newInstance();
		updInfoForm.setProdId(productId+"");
		updInfoForm.setCount(updInfoForm.getCount() + 1);
		updInfoForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		WorkFlowData data = WorkFlowDataProdRecordMgr.getInstance().updateProdRecordInfo(workFlowDataId, productId+"", updInfoForm);
		System.out.println(JsonUtil.getInstance().toJson(data));

		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
