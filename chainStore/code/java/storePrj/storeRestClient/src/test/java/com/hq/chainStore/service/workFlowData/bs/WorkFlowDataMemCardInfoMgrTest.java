package com.hq.chainStore.service.workFlowData.bs;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.MemCardInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataMemCardInfoMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	private static long workFlowDataId = 5L;
	private static String memTypeId = "2";
	
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
		MemCardInfoForm inputForm = MemCardInfoForm.newInstance();
		inputForm.setMemTypeId(memTypeId);
		inputForm.setCost(RandomUtils.nextFloat(1000f, 2000f));
		Set<Long> buserIds = new HashSet<Long>();
		buserIds.add(RandomUtils.nextLong(1L, 50L));
		inputForm.setBuserIds(buserIds);
		WorkFlowData record = WorkFlowDataMemCardInfoMgr.getInstance().addMemCardInfo(workFlowDataId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MemCardInfoForm inputForm = MemCardInfoForm.newInstance();
		inputForm.setCost(RandomUtils.nextFloat(1000f, 2000f));
		WorkFlowData record = WorkFlowDataMemCardInfoMgr.getInstance().updateMemCardInfo(workFlowDataId, memTypeId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
