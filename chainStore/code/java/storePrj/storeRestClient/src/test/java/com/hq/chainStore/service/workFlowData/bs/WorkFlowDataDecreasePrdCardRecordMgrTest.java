package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataDecreasePrdCardRecordMgrTest {
	private static Boss boss;
	private static String decreasePrdCardId = "0_38_65";
	
	
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
		long workFlowDataId = 292L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		DecreasePrdCardAddForm addForm = DecreasePrdCardAddForm.newInstance();
		addForm.setCardTypeId(RandomUtils.nextInt(0, 100)+"");
		addForm.setStatus(RandomUtils.nextInt(0, 2));
		addForm.setCount(RandomUtils.nextInt(0, 10));
		addForm.setPrdId(65L);
		WorkFlowData record = WorkFlowDataDecreasePrdCardRecordMgr.getInstance().addDecreasePrdCardRecord(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddList() {
		long workFlowDataId = 292L;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<DecreasePrdCardAddForm> inputForms = new ArrayList<DecreasePrdCardAddForm>();
		DecreasePrdCardAddForm addForm = DecreasePrdCardAddForm.newInstance();
		addForm.setCardTypeId(RandomUtils.nextInt(0, 100)+"");
		addForm.setStatus(RandomUtils.nextInt(0, 2));
		addForm.setCount(RandomUtils.nextInt(0, 10));
		addForm.setPrdId(RandomUtils.nextInt(1, 50));
		inputForms.add(addForm);
		DecreasePrdCardAddListForm addListForm = DecreasePrdCardAddListForm.newInstance();
		addListForm.setDecreasePrdCardAddForms(inputForms);
		WorkFlowData data = WorkFlowDataDecreasePrdCardRecordMgr.getInstance().addDecreasePrdCardRecordList(workFlowDataId, addListForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		long workFlowDataId = 292L;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataDecreasePrdCardRecordMgr.getInstance().deleteDecreasePrdCardRecord(workFlowDataId, decreasePrdCardId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		long workFlowDataId = 292L;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		DecreasePrdCardUpdateForm updateForm = DecreasePrdCardUpdateForm.newInstance();
		updateForm.setDecreasePrdCardId(decreasePrdCardId);
		updateForm.setCount(RandomUtils.nextInt(0, 10));
		WorkFlowDataDecreasePrdCardRecordMgr.getInstance().updateDecreasePrdCardRecord(workFlowDataId, decreasePrdCardId, updateForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
