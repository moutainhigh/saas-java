package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataGoodsRecordMgrTest {
	private static Boss boss;
	private static long workFlowDataId = 261L;
	private static String goodsId = "9";
	
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
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsRecordAddForm addForm = GoodsRecordAddForm.newInstance();
		addForm.setGoodsId(goodsId);
		addForm.setCount(RandomUtils.nextInt(0, 10));
		addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		WorkFlowData record = WorkFlowDataGoodsRecordMgr.getInstance().addGoodsRecord(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<GoodsRecordAddForm> inputForms = new ArrayList<GoodsRecordAddForm>();
		GoodsRecordAddForm addForm = GoodsRecordAddForm.newInstance();
		addForm.setGoodsId(RandomUtils.nextInt(0, 100)+"");
		addForm.setCount(RandomUtils.nextInt(0, 10));
		addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		inputForms.add(addForm);
		GoodsRecordAddListForm addListForm = GoodsRecordAddListForm.newInstance();
		addListForm.setGoodsRecordAddForms(inputForms);
		WorkFlowData restResp = WorkFlowDataGoodsRecordMgr.getInstance().addGoodsRecordList(workFlowDataId, addListForm);
		
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowData record = WorkFlowDataGoodsRecordMgr.getInstance().deleteGoodsRecord(workFlowDataId, goodsId);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsRecordUpdateForm updateForm = GoodsRecordUpdateForm.newInstance();
		updateForm.setGoodsId(goodsId);
		updateForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		updateForm.setCount(RandomUtils.nextInt(0, 10));
		WorkFlowDataGoodsRecordMgr.getInstance().updateGoodsRecord(workFlowDataId, goodsId, updateForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
