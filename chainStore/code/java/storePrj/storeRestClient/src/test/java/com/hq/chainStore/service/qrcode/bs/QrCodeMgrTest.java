package com.hq.chainStore.service.qrcode.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.chainStore.service.qrcode.apiData.QrCodeResp;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class QrCodeMgrTest {
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
	public void testGenQrCode() throws Exception{
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		QrCodeAPIForm apiForm = QrCodeAPIForm.newInstance();
		apiForm.setContent("https://www.zhimeitimes.com/file/android/zhimeitong.apk");
		apiForm.setLogoUrl("https://www.zhimeitimes.com/img/logo/store/zhimeitong-logo.png");
		QrCodeResp resp = QrCodeMgr.getInstance().genQrCode(apiForm);
		System.out.println(resp.getImgUrl());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
