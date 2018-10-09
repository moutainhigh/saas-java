package com.hq.chainStore.service.storeLeaguerInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelAddForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelRemoveForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelUpdateForm;
import com.hq.chainStore.service.storeLeaguerInfo.data.LeaguerLabel;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;


public class LeaguerLabelTest {
	private static Boss boss;
	private static long storeId=21L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
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
		StoreLeaguerInfo storeInfo = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		Collection<LeaguerLabel> values = storeInfo.getLeaguerLabelMap().values();
		for (LeaguerLabel leaguerLabel : values) {
			System.out.println(JsonUtil.getInstance().toJson(leaguerLabel));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddLabels() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreLeaguerInfo storeInfo = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		long labelIndex = storeInfo.getLabelIndex();
		List<LeaguerLabelAddForm> listForms = new ArrayList<LeaguerLabelAddForm>(); 
		for (int i = 0; i < 2; i++) {
			LeaguerLabelAddForm inputForm = LeaguerLabelAddForm.newInstance();
			inputForm.setIndex(labelIndex+i+1);
			inputForm.setName("标签-"+RandomUtils.nextInt(10, 100));
			listForms.add(inputForm);
		}
		StoreLeaguerInfoMgr.getInstance().addLeaguerLabels(storeId, listForms);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddLabel() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreLeaguerInfo storeInfo = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		LeaguerLabelAddForm inputForm = LeaguerLabelAddForm.newInstance();
		inputForm.setIndex(storeInfo.getLabelIndex()+1);
		inputForm.setName("标签-66");
		StoreLeaguerInfoMgr.getInstance().addLeaguerLabel(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testDelLabel() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerLabelRemoveForm inputForm = LeaguerLabelRemoveForm.newInstance();
		inputForm.setId("3");
		StoreLeaguerInfoMgr.getInstance().removeLeaguerLabel(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdateLabel() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerLabelUpdateForm inputForm = LeaguerLabelUpdateForm.newInstance();
		inputForm.setId("3");
		inputForm.setName("标签-"+RandomUtils.nextInt(10, 100));
		StoreLeaguerInfoMgr.getInstance().updateLeaguerLabel(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
