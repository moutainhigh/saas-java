package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataPrdCardRecordMgrTest {
	private static Boss boss;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}
	
	@Test
	public void testAdd() {
		long workFlowDataId = 261L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardAddForm addForm = PrdCardAddForm.newInstance();
		addForm.setPrdCardTypeId(RandomUtils.nextInt(0, 10)+"");
		addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		addForm.setCount(RandomUtils.nextInt(0, 10));
		WorkFlowData record = WorkFlowDataPrdCardRecordMgr.getInstance().addPrdCardRecord(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddList() {
		long workFlowDataId = 261L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<PrdCardAddForm> inputForms = new ArrayList<PrdCardAddForm>();
		PrdCardAddForm addForm = PrdCardAddForm.newInstance();
		addForm.setPrdCardTypeId(RandomUtils.nextInt(0, 10)+"");
		addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		addForm.setCount(RandomUtils.nextInt(0, 10));
		inputForms.add(addForm);
		PrdCardAddListForm addListForm = PrdCardAddListForm.newInstance();
		addListForm.setPrdCardAddForms(inputForms);
		WorkFlowData restResp = WorkFlowDataPrdCardRecordMgr.getInstance().addPrdCardRecordList(workFlowDataId, addListForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		long workFlowDataId = 261L;
		String prdCardId="";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowData data = WorkFlowDataPrdCardRecordMgr.getInstance().deletePrdCardRecord(workFlowDataId, prdCardId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		long workFlowDataId = 261L;
		String prdCardId="";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardUpdateForm updateForm = PrdCardUpdateForm.newInstance();
		updateForm.setPrdCardTypeId(prdCardId);
		updateForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		updateForm.setCount(RandomUtils.nextInt(0, 10));
		WorkFlowDataPrdCardRecordMgr.getInstance().updatePrdCardRecord(workFlowDataId, prdCardId, updateForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
