package com.hq.chainStore.service.appVersion.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.appVersion.apiData.AddAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionStatusForm;
import com.hq.chainStore.service.appVersion.data.AppVersion;
import com.hq.chainStore.service.appVersion.data.AppVersionStatusEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class PrdEnvAppVersionMgrTest {
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
	public void testAddZMT() {
		String appName = "智美通";
		String version="2.0.3";
		String downIP="https://www.zhimeitimes.com";
		addZmt(appName, version, downIP);
	}
	
	private void addZmt(String appName, String version, String downIP) {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion newVersion = AppVersionMgr.getInstance().findZmtVersion();
		if(newVersion != null){
			UpdAppVersionStatusForm updAppVersionStatusForm=UpdAppVersionStatusForm.newInstance();
			updAppVersionStatusForm.setStatus(AppVersionStatusEnum.UNVALID.ordinal());
			AppVersionMgr.getInstance().updAppVersionStatus(newVersion.getId(), updAppVersionStatusForm);
		}
		AddAppVersionForm addAppVersionForm = AddAppVersionForm.newInstance();
		addAppVersionForm.setAppName(appName);
		addAppVersionForm.setAppUrl(downIP+"/file/android/zhimeitong.apk");
		addAppVersionForm.setAppVersion(version);
		addAppVersionForm.setDescript(appName+version+"版本");
		AppVersion appVersion = AppVersionMgr.getInstance().addAppVersion(addAppVersionForm);
		System.out.println(appVersion.getAppUrl());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion newVersion = AppVersionMgr.getInstance().findZmtVersion();
		System.out.println(newVersion.getDescript());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetUnAuth() {
		AppVersion newVersion = AppVersionMgr.getInstance().findByNameUnAuth("智美通PC");
		System.out.println(JsonUtil.getInstance().toJson(newVersion));
	}

}
