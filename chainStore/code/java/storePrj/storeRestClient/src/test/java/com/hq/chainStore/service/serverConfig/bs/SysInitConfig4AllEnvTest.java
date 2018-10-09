package com.hq.chainStore.service.serverConfig.bs;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.hq.chainStore.service.appVersion.apiData.AddAppVersionForm;
import com.hq.chainStore.service.appVersion.apiData.UpdAppVersionStatusForm;
import com.hq.chainStore.service.appVersion.bs.AppVersionMgr;
import com.hq.chainStore.service.appVersion.data.AppVersion;
import com.hq.chainStore.service.appVersion.data.AppVersionStatusEnum;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigAddForm;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class SysInitConfig4AllEnvTest {
	private static Boss boss;
	
	@Test
	public void add2MultiEnv() {
		String[] services = {"http://192.168.40.221/storems/ws/v1","http://192.168.40.220/storems/ws/v1"};//"https://www.zhimeitimes.com:9110/storems/ws/v1"
		TestEnv.initTestEnv();
		for (String service : services) {
			RestClientCfg.init(service);
			boss = Boss.newBoss(BRobot.newRandom());
			boss.getRobot().getData().setPhone(13660623953L);
			boss.getRobot().getData().setPassword("123456");
			boss.login();
			
			String baseUrl = StringUtils.substringBefore(service, "/storems");
			String appName = "智美通";
			String version = "2.0.0";
			addZMTConfig(appName, version, baseUrl);
			addZmtVersion(appName, version, baseUrl);
		}
	}
	
	private void addZmtVersion(String appName, String version, String baseUrl) {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppVersion oldVersion = AppVersionMgr.getInstance().findZmtVersion();
		
		if(!checkVersion(oldVersion.getAppVersion(), version)) {
			return ;
		}
		
		if(oldVersion != null){
			UpdAppVersionStatusForm updAppVersionStatusForm=UpdAppVersionStatusForm.newInstance();
			updAppVersionStatusForm.setStatus(AppVersionStatusEnum.UNVALID.ordinal());
			AppVersionMgr.getInstance().updAppVersionStatus(oldVersion.getId(), updAppVersionStatusForm);
		}
		AddAppVersionForm addAppVersionForm = AddAppVersionForm.newInstance();
		addAppVersionForm.setAppName(appName);
		addAppVersionForm.setAppUrl(baseUrl+"/file/android/zhimeitong.apk");
		addAppVersionForm.setAppVersion(version);
		addAppVersionForm.setDescript(appName+version+"版本");
		AppVersionMgr.getInstance().addAppVersion(addAppVersionForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private boolean checkVersion(String oldVersion, String newVersion) {
		oldVersion = oldVersion.replaceAll("\\.", "");
		newVersion = newVersion.replaceAll("\\.", "");
		if(Integer.valueOf(oldVersion) >= Integer.valueOf(newVersion)) {
			return false;
		}
		return true;
	}
	
	private void addZMTConfig(String appName, String version, String baseUrl) {
		ServerConfig serverConfig = ServerConfigMgr.getInstance().getServerConfigFromMS(appName, "", version);
		if(serverConfig == null || (!serverConfig.getAppVersion().equals(version))) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			ServerConfigAddForm addForm = ServerConfigAddForm.newInstance();
			addForm.setAppNameCh(appName);
			addForm.setAppNameEn("zhimeitong");
			addForm.setAppVersion(version);
			addForm.setImgHost(baseUrl);
			addForm.setStoreService(baseUrl+"/storems/ws/v1");
			
			//将来估计不需要
			addForm.setCustomerService(baseUrl+"/customerms/ws/v1");
			addForm.setOrderService(baseUrl+"/orderms/ws/v1");
			addForm.setGeneralizeUrl(baseUrl+"/storeweb/client/storeIndex.html#/store");
			
			addForm.setAttributeMap(buildZmtAttributeMap(baseUrl));
			ServerConfigMgr.getInstance().addServerConfig(addForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
	
	private Map<String, String> buildZmtAttributeMap(String baseUrl){
		Map<String, String> attributeMap = new HashMap<String, String>();
		//加载遮盖的时间 单位：秒
		attributeMap.put("showTime", "7");
		//智美通 logo地址
		attributeMap.put("logoPath", "img/logo/store/zhimeitong-logo.png");
		//会员卡图片个数
		attributeMap.put("MembershipCardCount", "6");
		//次数图片个数
		attributeMap.put("ConsumptionCardCount", "5");
		//protocol 协议链接
		attributeMap.put("protocolUrl", baseUrl+"/protocol/index.html");
		attributeMap.put("devStoreService", "https://www.zhimeitimes.com/dev/storems/ws/v1");
		attributeMap.put("devImgHost", "https://www.zhimeitimes.com");
		return attributeMap;
	}

}
