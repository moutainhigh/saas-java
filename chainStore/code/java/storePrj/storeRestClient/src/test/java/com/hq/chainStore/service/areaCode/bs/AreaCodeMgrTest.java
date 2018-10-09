package com.hq.chainStore.service.areaCode.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainStore.service.areaCode.data.AreaCode;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class AreaCodeMgrTest {
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
	
//	@Test
//	public void testAdd() {
//		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		AreaCodeAddForm addForm = AreaCodeAddForm.newInstance();
//		addForm.setAreaCode("+65");
//		addForm.setCountryCh("新加坡");
//		addForm.setCountryEn("Singapore");
//		AreaCode areaCode = AreaCodeMgr.getInstance().addAreaCode(addForm);
//		System.out.println(JsonUtil.getInstance().toJson(areaCode));
//		AccessTokenMgr.getInstance().removeOpIdTL();
//	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AreaCode areaCode = AreaCodeMgr.getInstance().getAreaCode(1L);
		System.out.println(JsonUtil.getInstance().toJson(areaCode));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindUnAuth() {
		List<AreaCode> areaCodes = AreaCodeMgr.getInstance().findByCondUnAuth(AreaCodeQueryForm.newInstance());
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<AreaCode> areaCodes = AreaCodeMgr.getInstance().findByCond(AreaCodeQueryForm.newInstance());
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
