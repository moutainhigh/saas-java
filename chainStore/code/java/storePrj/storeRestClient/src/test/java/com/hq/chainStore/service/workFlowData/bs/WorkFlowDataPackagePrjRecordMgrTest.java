package com.hq.chainStore.service.workFlowData.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.PackagePrjRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataSynDataHolder;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class WorkFlowDataPackagePrjRecordMgrTest {
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
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7565L;
		PackagePrjRecordAddForm inputForm = PackagePrjRecordAddForm.newInstance();
		inputForm.setPackagePrjId("16052_1");
		inputForm.setCount(RandomUtils.nextInt(0, 10));
		inputForm.setOldPrice(RandomUtils.nextFloat(1000f, 2000f));
		inputForm.setRecordType(0);
		WorkFlowDataPackagePrjRecordMgr.getInstance().addPackagePrjRecord(workFlowDataId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7565L;
		String packagePrjId = "16052_1";
		WorkFlowDataPackagePrjRecordMgr.getInstance().deletePackagePrjRecord(workFlowDataId, packagePrjId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7565L;
		String packagePrjId = "16052_1";
		WorkFlowData data = WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId), storeId);
		System.out.println(data.getPackagePrjRecordMap().get(packagePrjId).getCount());
		PackagePrjRecordUpdateForm inputForm = PackagePrjRecordUpdateForm.newInstance();
		inputForm.setPackagePrjId(packagePrjId);
		inputForm.setCount(100);
		inputForm.setOldPrice(RandomUtils.nextFloat(1000f, 2000f));
		inputForm.setRecordType(0);
		WorkFlowDataPackagePrjRecordMgr.getInstance().updatePackagePrjRecord(workFlowDataId, packagePrjId, inputForm);
		
		WorkFlowData data2 = WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId), storeId);
		System.out.println(data2.getPackagePrjRecordMap().get(packagePrjId).getCount());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHolder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7564L;
		long workFlowDataId2 = 7565L;
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId), storeId);
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId2), storeId);
		
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId), storeId);
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId2), storeId);
		
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId), storeId);
		WorkFlowDataSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(workFlowDataId2), storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
