package com.hq.chainStore.service.storeConfig.bs;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.storeConfig.apiData.AppointTimeUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.BaseAttributeStatusForm;
import com.hq.chainStore.service.storeConfig.apiData.CancelAppointUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.ExpandAttributeUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.LeaguerTypeUpdateForm;
import com.hq.chainStore.service.storeConfig.data.StoreConfig;
import com.hq.chainStore.service.storeConfig.data.StoreConfigSynDataHolder;
import com.hq.chainStore.service.storeConfig.data.appoint.CancelAppointConfig;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerTypeConfig;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class StoreConfigMgrTest {
	private static Boss boss;
	private static long storeId=0L;

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
	public void testGetTime() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long startTime = System.currentTimeMillis();
		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(20L);
		System.out.println(JsonUtil.getInstance().toJson(storeConfig.getAppointConfig().getAppointTimeConfig()));
		System.out.println(System.currentTimeMillis() - startTime);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(16053L);
		System.out.println(JsonUtil.getInstance().toJson(storeConfig.getAppointConfig().getAppointTimeConfig()));
		
		System.out.println("=================================================");
		List<CancelAppointConfig> cancelAppointConfigList = storeConfig.takeCancelAppointConfigList();
		for (CancelAppointConfig data : cancelAppointConfigList) {
			System.out.println(data.getId()+"\t"+data.getReason());
		}
		System.out.println("=================================================");
		List<LeaguerBaseAttribute> leaguerBaseAttributes = storeConfig.takeLeaguerBaseAttributeList();
		for (LeaguerBaseAttribute data : leaguerBaseAttributes) {
			System.out.println(data.getAttributeName()+"\t"+data.getLabel());
		}
		System.out.println("=================================================");
		List<LeaguerExpandAttribute> leaguerExpandAttributes = storeConfig.takeLeaguerExpandAttributeList();
		for (LeaguerExpandAttribute data : leaguerExpandAttributes) {
			System.out.println(data.getLabel()+"\t"+data.getSort());
		}
		System.out.println("=================================================");
		List<LeaguerOriginConfig> leaguerOriginConfigs = storeConfig.takeLeaguerOriginConfigList();
		for (LeaguerOriginConfig data : leaguerOriginConfigs) {
			System.out.println(data.getOriginName()+"\t"+data.getId());
		}
		
		System.out.println("=================================================");
		Collection<LeaguerTypeConfig> leaguerTypeConfigs = storeConfig.takeLeaguerTypeConfigMap().values();
		for (LeaguerTypeConfig data : leaguerTypeConfigs) {
			System.out.println(data.getTypeName()+"\t"+data.getId());
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testOrigin() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(storeId);
//		int leaguerOriginConfigIndex = storeConfig.getLeaguerConfig().getLeaguerOriginConfigIndex();
//		LeaguerOriginAddForm addForm = LeaguerOriginAddForm.newInstance();
//		addForm.setId(leaguerOriginConfigIndex+1);
//		addForm.setOriginName("美团"+RandomUtils.nextInt(100, 1000));
//		StoreConfigMgr.getInstance().addLeaguerOrigin(storeId, addForm);
		
//		LeaguerOriginUpdateForm updateForm = LeaguerOriginUpdateForm.newInstance();
//		updateForm.setId(1);
//		updateForm.setOriginName("路过");
//		StoreConfigMgr.getInstance().updateLeaguerOrigin(storeId, updateForm);
//		
//		LeaguerOriginRemoveForm removeForm = LeaguerOriginRemoveForm.newInstance();
//		removeForm.setId(1);
//		StoreConfigMgr.getInstance().removeLeaguerOrigin(storeId, removeForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(storeId);
		Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap = storeConfig.getLeaguerConfig().getLeaguerTypeConfigMap();
		leaguerTypeConfigMap.get(1).setConsumeDates(20);
		LeaguerTypeUpdateForm inputForm = LeaguerTypeUpdateForm.newInstance();
		inputForm.setLeaguerTypeConfigMap(leaguerTypeConfigMap);
		StoreConfigMgr.getInstance().updateLeaguerType(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testBaseAttribut() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(storeId);
		LeaguerBaseAttribute leaguerBaseAttribute = storeConfig.getLeaguerConfig().getLeaguerBaseAttributes().get(0);
		System.out.println(JsonUtil.getInstance().toJson(leaguerBaseAttribute));

		BaseAttributeStatusForm inputForm = BaseAttributeStatusForm.newInstance();
		FastBeanCopyer.getInstance().copy(leaguerBaseAttribute, inputForm);
		inputForm.setRequire(1);
		StoreConfigMgr.getInstance().updateBaseAttribute(storeId, inputForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAppointTime() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppointTimeUpdateForm inputForm = AppointTimeUpdateForm.newInstance();
		inputForm.setEndTime("21:00");
		inputForm.setStartTime("09:00");
		StoreConfigMgr.getInstance().updateAppointTime(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testReason() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(storeId);
//		int cancelAppointIndex = storeConfig.getAppointConfig().getCancelAppointIndex();
//		CancelAppointAddForm inputForm = CancelAppointAddForm.newInstance();
//		inputForm.setId(cancelAppointIndex+1);
//		inputForm.setReason("测试");
//		StoreConfigMgr.getInstance().addCancelReason(storeId, inputForm);
		
		CancelAppointUpdateForm inputForm = CancelAppointUpdateForm.newInstance();
		inputForm.setId(5);
		inputForm.setReason("老迟到8888");
		StoreConfigMgr.getInstance().updateCancelReason(storeId, inputForm);
		
//		CancelAppointRemoveForm inputForm = CancelAppointRemoveForm.newInstance();
//		inputForm.setId(5);
//		StoreConfigMgr.getInstance().removeCancelReason(storeId, inputForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testExpandAttribute() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		StoreConfig storeConfig = StoreConfigMgr.getInstance().findStoreConfigByStoreId(storeId);
//		int index = storeConfig.getLeaguerConfig().getLeaguerExpandAttributeIndex();
//		ExpandAttributeAddForm inputForm = ExpandAttributeAddForm.newInstance();
//		inputForm.setId(index+1);
//		inputForm.setAttributeType(0);
//		inputForm.setLabel("测试2");
//		inputForm.setTips("提示2");
//		StoreConfigMgr.getInstance().addExpandAttribute(storeId, inputForm);
		
//		ExpandAttributeSortForm inputForm = ExpandAttributeSortForm.newInstance();
//		inputForm.setId(1);
//		inputForm.setSort(1);
//		StoreConfigMgr.getInstance().sortExpandAttribute(storeId, inputForm);
		
//		ExpandAttributeStatusForm inputForm = ExpandAttributeStatusForm.newInstance();
//		inputForm.setId(1);
//		inputForm.setRequire(0);
//		inputForm.setStatus(1);
//		StoreConfigMgr.getInstance().statusExpandAttribute(storeId, inputForm);
		
		ExpandAttributeUpdateForm inputForm = ExpandAttributeUpdateForm.newInstance();
		inputForm.setId(1);
		inputForm.setLabel("ooooo");
		inputForm.setAttributeType(1);
		inputForm.setTips("kkkkkkkkkkkkk");
		StoreConfigMgr.getInstance().updateExpandAttribute(storeId, inputForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHolder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreConfig data = StoreConfigSynDataHolder.getInstance().getData(boss.getId()+"", storeId+"");
		System.out.println(JsonUtil.getInstance().toJson(data));
		
		LeaguerExpandAttribute expandAttribute = data.getLeaguerConfig().getLeaguerExpandAttributeMap().get(1);
		ExpandAttributeUpdateForm inputForm = ExpandAttributeUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(expandAttribute, inputForm);
		inputForm.setTips("kkkkkkkkkkkkk");
		StoreConfigMgr.getInstance().updateExpandAttribute(storeId, inputForm);
		
		StoreConfig data2 = StoreConfigSynDataHolder.getInstance().getData(boss.getId()+"", storeId+"");
		System.out.println(JsonUtil.getInstance().toJson(data2));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	

}
