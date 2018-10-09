package com.hq.chainStore.service.appVersion.bs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.appVersion.apiData.AddAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionInfoForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionStatusForm;
import com.hq.chainStore.service.appVersion.data.AppVersion;
import com.hq.chainStore.service.appVersion.data.AppVersionStatusEnum;
import com.hq.chainStore.service.common.FileResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

public class AppVersionMgrTest {
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
	public void testUploadApk(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String filePath = "F:\\honkon\\apk\\zhimeitong-v1.3.4.apk";
		FileResp fileResp = UploadApkMgr.getInstance().uploadApk("file/android/zhimeitong.apk", new File(filePath));
		if(fileResp!=null) {
			System.out.println(fileResp.getImgPathList());
		}
		
		try {
			ApkFile apkFile = new ApkFile(new File(filePath));
			ApkMeta apkMeta = apkFile.getApkMeta();
			String appName = apkMeta.getName();
			String version = apkMeta.getVersionName();
			String storeService = RestClientCfg.getStoreService();
			String downIP = StringUtils.substringBefore(storeService, "/storems");
			addZmt(appName, version, downIP);
			apkFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		AddAppVersionForm addAppVersionForm = AddAppVersionForm.newInstance();
		addAppVersionForm.setAppName("智美通");
		addAppVersionForm.setAppUrl("http://39.106.13.113:9116/file/android/zhimeitong.apk");
		addAppVersionForm.setAppVersion("1.0");
		addAppVersionForm.setDescript("智美通1.0版本");
		AppVersion appVersion = AppVersionMgr.getInstance().addAppVersion(addAppVersionForm);
		
		UpdAppVersionInfoForm updAppVersionInfoForm = UpdAppVersionInfoForm.newInstance();
		FastBeanCopyer.getInstance().copy(appVersion, updAppVersionInfoForm);
		updAppVersionInfoForm.setAppVersion("1.1");
		AppVersionMgr.getInstance().updAppVersionInfo(appVersion.getId(), updAppVersionInfoForm);
		
		AppVersion newVersion = AppVersionMgr.getInstance().findByName(appVersion.getAppName());
		
		System.out.println(appVersion.getAppVersion());
		System.out.println(newVersion.getAppVersion());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddZMTPC() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion newVersion = AppVersionMgr.getInstance().findZmtVersion();
		UpdAppVersionStatusForm updAppVersionStatusForm=UpdAppVersionStatusForm.newInstance();
		updAppVersionStatusForm.setStatus(AppVersionStatusEnum.UNVALID.ordinal());
		AppVersionMgr.getInstance().updAppVersionStatus(newVersion.getId(), updAppVersionStatusForm);
		
		String appName = "智美通PC";
		String version="1.0.0";
		AddAppVersionForm addAppVersionForm = AddAppVersionForm.newInstance();
		addAppVersionForm.setAppName(appName);
		addAppVersionForm.setAppUrl("");
		addAppVersionForm.setAppVersion(version);
		String descript=StringUtils.join(fileText(),"");
		addAppVersionForm.setDescript(descript);
		AppVersion appVersion = AppVersionMgr.getInstance().addAppVersion(addAppVersionForm);
		System.out.println(appVersion.getAppVersion());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddZMK() {
		String appName = "智美客";
		String version="1.1.0";
		String downIP="192.168.40.221";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AddAppVersionForm addAppVersionForm = AddAppVersionForm.newInstance();
		addAppVersionForm.setAppName(appName);
		addAppVersionForm.setAppUrl("http://"+downIP+":9116/file/android/zhimeike-v"+version+".apk");
		addAppVersionForm.setAppVersion(version);
		addAppVersionForm.setDescript(appName+version+"版本");
		AppVersion appVersion = AppVersionMgr.getInstance().addAppVersion(addAppVersionForm);
		System.out.println(appVersion.getAppUrl());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateStatus() {
		long appVersionId = 9L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		UpdAppVersionStatusForm updAppVersionStatusForm=UpdAppVersionStatusForm.newInstance();
		updAppVersionStatusForm.setStatus(AppVersionStatusEnum.UNVALID.ordinal());
		AppVersionMgr.getInstance().updAppVersionStatus(appVersionId, updAppVersionStatusForm);
		
		AppVersion newVersion = AppVersionMgr.getInstance().getAppVersion(appVersionId);
		System.out.println(newVersion.getStatus());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		AppVersion appVersion = AppVersionMgr.getInstance().getAppVersion(3L);
		AppVersion appVersion = AppVersionMgr.getInstance().findByName("智美通PC");
		System.out.println(appVersion.getAppVersion());
//		UpdAppVersionInfoForm updAppVersionInfoForm = UpdAppVersionInfoForm.newInstance();
//		FastBeanCopyer.getInstance().copy(appVersion, updAppVersionInfoForm);
//		String descript=StringUtils.join(fileText(),"");
//		updAppVersionInfoForm.setDescript(descript);
//		AppVersionMgr.getInstance().updAppVersionInfo(appVersion.getId(), updAppVersionInfoForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion newVersion = AppVersionMgr.getInstance().findZmtVersion();
//		AppVersion newVersion = AppVersionMgr.getInstance().getAppVersion(10L);
		System.out.println(newVersion.getDescript());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetUnAuth() {
		AppVersion newVersion = AppVersionMgr.getInstance().findByNameUnAuth("智美通PC");
		System.out.println(JsonUtil.getInstance().toJson(newVersion));
	}
	
	@Test
	public void testGetZMK() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion newVersion = AppVersionMgr.getInstance().findByName("智美客");
		System.out.println(newVersion.getAppVersion());
		System.out.println(newVersion.getAppUrl());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private List<String> fileText(){
		List<String> contents = new ArrayList<String>();
		try {
			File file = new File("F:\\honkon\\SVN\\ZMT\\Code\\PC\\protocol\\PC\\PC Software Protocol.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");// 考虑到编码格式
				BufferedReader bufferedReader = null;
				try {
					bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						contents.add(lineTxt);
					}
				} finally {
					if (bufferedReader != null) {
						bufferedReader.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

}
