package com.hq.chainClient.service.areaCode.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainClient.service.areaCode.data.AreaCode;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class AreaCodeClientMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	private void login() {
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
	}

	@Test
	public void testFindUnAuth() {
		List<AreaCode> areaCodes = AreaCodeClientMgr.getInstance().findByCondUnAuth(AreaCodeQueryForm.newInstance());
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
	}
	
	@Test
	public void testList() {
		login();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<AreaCode> areaCodes = AreaCodeClientMgr.getInstance().findByCond(AreaCodeQueryForm.newInstance());
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
