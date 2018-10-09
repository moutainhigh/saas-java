package com.hq.chainStore.service.workFlowData.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataLeaguerInfoMgrTest {
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
	public void testAdd() {
		long workFlowDataId = 300L;
		String leaguerId = boss.getRandomLeaguer(workFlowDataId).getId();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerInfoForm inputForm = LeaguerInfoForm.newInstance();
		inputForm.setLeaguerId(leaguerId);
		inputForm.setLeaguerName("客户-9352");
		inputForm.setPicBefore("img/logo/store/zhimeitong-logo.png");
		WorkFlowData data = WorkFlowDataLeaguerInfoMgr.getInstance().addLeaguerInfo(workFlowDataId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		long workFlowDataId = 8L;
		String leaguerId = boss.getRandomLeaguer(workFlowDataId).getId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		cuserCareId = 0;
//	    cuserDocId = 0;
//	    followUserId = 55;
		
//		LeaguerInfo leaguerInfo = WorkFlowDataLeaguerInfoMgr.getInstance().getLeaguerInfo(workFlowDataId, leaguerId);
//		System.out.println(JsonUtil.getInstance().toJson(leaguerInfo));
		LeaguerInfoForm inputForm = LeaguerInfoForm.newInstance();
//		FastBeanCopyer.getInstance().copy(leaguerInfo, inputForm);
		
		inputForm.setFollowUserId(55L);
		inputForm.setCuserCareId(0L);
		inputForm.setCuserDocId(0L);
		inputForm.setLeaguerId("21_66825");
//		inputForm.setPicAfter("img/logo/store/zhimeitong-logo.png");
//		inputForm.setPicBefore("img/logo/store/zhimeitong-logo.png");
//		inputForm.setCuserDocId(23L);
		WorkFlowData data = WorkFlowDataLeaguerInfoMgr.getInstance().updateLeaguerInfo(workFlowDataId, leaguerId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
