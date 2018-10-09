package com.hq.chainStore.service.serverConfig.bs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigAddForm;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.chainStore.service.serverConfig.apiData.ServerConfigUpdForm;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ServerConfigMgrTest {
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
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		String storeService = RestClientCfg.getStoreService();
		String baseUrl = StringUtils.substringBefore(storeService, "/storems");
		System.out.println(baseUrl);
		ServerConfigAddForm addForm = ServerConfigAddForm.newInstance();
		addForm.setAppNameCh("智美通");
		addForm.setAppNameEn("zhimeitong");
		addForm.setAppVersion("2.0.3");
		addForm.setImgHost(baseUrl);
		addForm.setStoreService(baseUrl+"/storems/ws/v1");
		
		addForm.setAttributeMap(buildZmtAttributeMap(baseUrl));
		ServerConfigMgr.getInstance().addServerConfig(addForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddZMK() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		String storeService = RestClientCfg.getStoreService();
		String baseUrl = StringUtils.substringBefore(storeService, "/storems");
		
		ServerConfigAddForm addForm = ServerConfigAddForm.newInstance();
		addForm.setAppNameCh("智美客");
		addForm.setAppNameEn("zhimeike");
		addForm.setAppVersion("1.1.0");
		addForm.setImgHost(baseUrl);
		addForm.setCustomerService(baseUrl+"/customerms/ws/v1");
		addForm.setStoreService(baseUrl+"/storems/ws/v1");
		addForm.setOrderService(baseUrl+"/orderms/ws/v1");
		
		Map<String, String> attributeMap = new HashMap<String, String>();
		addForm.setAttributeMap(attributeMap);
		ServerConfigMgr.getInstance().addServerConfig(addForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testUpdZMT() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ServerConfig serverConfig = ServerConfigMgr.getInstance().findOneServerConfig("智美通", "", "2.0.0");
		ServerConfigUpdForm updForm = ServerConfigUpdForm.newInstance();
		FastBeanCopyer.getInstance().copy(serverConfig, updForm);
		
		String storeService = RestClientCfg.getStoreService();
		String baseUrl = StringUtils.substringBefore(storeService, "/storems");
		
		updForm.setAttributeMap(buildZmtAttributeMap(baseUrl));
		System.out.println(JsonUtil.getInstance().toJson(updForm));
		ServerConfigMgr.getInstance().updServerConfig(serverConfig.getId(), updForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdZMT2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ServerConfig serverConfig = ServerConfigMgr.getInstance().findOneServerConfig("智美通", "", "1.2.2");
//		ServerConfig serverConfig = ServerConfigMgr.getInstance().getServerConfig(7L);
		System.out.println(serverConfig.getImgHost());
		ServerConfigUpdForm updForm = ServerConfigUpdForm.newInstance();
		FastBeanCopyer.getInstance().copy(serverConfig, updForm);
		updForm.setAppVersion("1.2.2");
		ServerConfigMgr.getInstance().updServerConfig(serverConfig.getId(), updForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdZMK() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ServerConfig serverConfig = ServerConfigMgr.getInstance().findOneServerConfig("智美客", "", "1.1.0");
		ServerConfigUpdForm updForm = ServerConfigUpdForm.newInstance();
		FastBeanCopyer.getInstance().copy(serverConfig, updForm);
		
		String storeService = RestClientCfg.getStoreService();
		String ip = StringUtils.substringBetween(storeService, "//","/").split(":")[0];
		
		updForm.setImgHost("http://"+ip+"");
		updForm.setCustomerService("http://"+ip+"/customerms/ws/v1");
		updForm.setStoreService("http://"+ip+"/storems/ws/v1/cm");
		updForm.setOrderService("http://"+ip+"/orderms/ws/v1/cm");
		updForm.setGeneralizeUrl("http://"+ip+"/storeweb/client/storeIndex.html#/store");
		ServerConfigMgr.getInstance().updServerConfig(serverConfig.getId(), updForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		ServerConfig serverConfig = ServerConfigMgr.getInstance().getServerConfig(1L);
		ServerConfig serverConfig = ServerConfigMgr.getInstance().findOneServerConfig("智美通", "", "2.0.3");
		System.out.println(JsonUtil.getInstance().toJson(serverConfig));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetUnAuth() {
		ServerConfig serverConfig = ServerConfigMgr.getInstance().getServerConfigFromMS("智美通", "", "2.0.3");
		System.out.println(JsonUtil.getInstance().toJson(serverConfig));
	}

	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ServerConfigQueryForm queryForm = ServerConfigQueryForm.newInstance();
		queryForm.setAppNameCh("智美通");
		queryForm.setPageItemCount(20);
		queryForm.setPageNo(1);
		List<ServerConfig> serverConfigs = ServerConfigMgr.getInstance().findServerConfigs(queryForm);
		for (ServerConfig serverConfig : serverConfigs) {
			System.out.println(JsonUtil.getInstance().toJson(serverConfig));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
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
		attributeMap.put("reportHost", baseUrl);
		return attributeMap;
	}

}
