package com.hq.chainStore.service.store.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.apiData.AlipayQrCodeApiData;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.apiData.StoreUpdateStatusData;
import com.hq.chainStore.service.store.apiData.UpdateStoreInfoApiData;
import com.hq.chainStore.service.store.apiData.WechatQrCodeApiData;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.store.data.StoreState;
import com.hq.chainStore.service.store.data.StoreSynDataHolder;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfoSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.opUser.OPSuper;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class StoreMgrTest {

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testTime(){
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(currentTimeMillis);
		long time = DateUtils.addMonths(new Date(), 1).getTime();
		System.out.println(time);
		long tt = time-currentTimeMillis;
		System.out.println(tt/(1000*60*60*24));
	}
	
	@Test
	public void testAdd() {
//		Boss boss = Boss.newBoss(BRobot.newRandom());
//		boss.reg();
//		boss.login();
		//老板登陆
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		
		long bossId = boss.getId();
		String storeName = "store_"+bossId;
		formInfo.setBossId(bossId).setName(storeName);
		
		Store store = boss.openStore(formInfo);
		System.out.println(JsonUtil.getInstance().toJson(store));
		Assert.assertEquals(bossId, store.getBossId());
		Assert.assertEquals(storeName, store.getName());
	}
	
	@Test
	public void testAddClerk() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		
		long bossId = boss.getId();
		String storeName = "store_"+bossId;
		formInfo.setBossId(bossId).setName(storeName);
		
		Store store = boss.openStore(formInfo);
		System.out.println(JsonUtil.getInstance().toJson(store));
		Assert.assertEquals(bossId, store.getBossId());
		Assert.assertEquals(storeName, store.getName());
	}

	@Test
	public void testGet() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		Assert.assertTrue("boss 注册成功", boss.reg());
		Assert.assertTrue("boss 登陆成功", boss.login());
		Store store = boss.openSimpleStore(1);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		Store storeFromGet = StoreMgr.getInstance().get(store.getId());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		checkStore(storeFromGet, store);
	}

	@Test
	public void testFindBUserStores() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		Assert.assertTrue("boss 注册成功", boss.reg());
		Assert.assertTrue("boss 登陆成功", boss.login());
		boss.openSimpleStore(1);
		boss.openSimpleStore(2);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		List<Store> storeList = StoreMgr.getInstance().findBUserStores(boss.getId(), 20, 0,0);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertEquals(0, storeList.size());
	}

	@Deprecated
	@Test
	public void testFindByName() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		Assert.assertTrue("boss 注册成功", boss.reg());
		Assert.assertTrue("boss 登陆成功", boss.login());
		Store store = boss.openSimpleStore(1);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		List<Store> storeList = StoreMgr.getInstance().findByName(store.getName(), 20, 1);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		Store storeFromGet = storeList.get(0);
		checkStore(store, storeFromGet);
	}
	
	private void checkStore(Store expected, Store result){
		Assert.assertEquals(expected .getId(), result.getId());
		Assert.assertEquals(expected.getBossId(), result.getBossId());
		Assert.assertEquals(expected.getName(), result.getName());
	}

	@Test
	public void testUpdateStoreInfo() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		Assert.assertTrue("boss 注册成功", boss.reg());
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		Store store = boss.openSimpleStore(1);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		
		Store storeFromHolder = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		checkStore(store, storeFromHolder);
		
		UpdateStoreInfoApiData inputData = UpdateStoreInfoApiData.newInstance();
		String descript = "修改后的描述信息";
		inputData.setDescript(descript);
		inputData.setStoreId(store.getId());
		StoreMgr.getInstance().updateStoreInfo(store.getId(), inputData );
		
		Store storeFromHolderAfterUpdate = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		
		Assert.assertEquals(descript, storeFromHolderAfterUpdate.getDescript());
		AccessTokenMgr.getInstance().removeOpIdTL();
		
	}
	
	@Test
	public void testUpdateStoreInfoByStoreId() {
		TestEnv.initTestEnv();
		//老板登陆
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(16055);
		boss.login();
	
		
		Store store = boss.getStore();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store storeFromHolder = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		checkStore(store, storeFromHolder);
		
		UpdateStoreInfoApiData inputData = UpdateStoreInfoApiData.newInstance();
		FastBeanCopyer.getInstance().copy(store, inputData);
		String descript = "修改后的描述信息";
		inputData.setDescript(descript);
		inputData.setStoreId(store.getId());
		
		List<String> disseminateImgs = new ArrayList<String>();
		disseminateImgs.add("img/store/6/2017_09_12_17_43b36d25-b739-4a41-83fd-a43f2848122c.png");
		disseminateImgs.add("img/store/6/2017_09_12_17_43b36d25-b739-4a41-83fd-a43f2848122c.png");
		disseminateImgs.add("img/store/6/2017_09_12_17_43b36d25-b739-4a41-83fd-a43f2848122c.png");
		inputData.setDisseminateImgs(disseminateImgs);
		
		StoreMgr.getInstance().updateStoreInfo(store.getId(), inputData );
		
		//Store storeFromHolderAfterUpdate = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		
		//Assert.assertEquals(descript, storeFromHolderAfterUpdate.getDescript());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testUpdateStoreState() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		Assert.assertTrue("boss 注册成功", boss.reg());
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		Store store = boss.openSimpleStore(1);
		boss.submit4Check(store.getId());
		
		OPSuper.getInstance().approveStore(store.getId());

		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		Store storeFresh = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		
		Assert.assertEquals(StoreState.Open, storeFresh.getStateEnum());
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoSynDataHolder.getInstance().getData(boss.getId(), storeFresh.getClerkInfoId());
		checkStoreClerkInfo(storeClerkInfo);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGenJoinStoreQrCode() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		boss.genJoinStoreQrCode(21L);
	}
	
	@Test
	public void testUploadAlipayQrCode() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AlipayQrCodeApiData info = AlipayQrCodeApiData.newInstance();
		info.setStoreId(21L);
		info.setAlipayImg("img/store/15/storePayQrCode/2017_08_21_11_4a36a01d-5945-4672-b882-399100137387.jpg");
		StoreMgr.getInstance().uploadAlipayQrCode(info);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUploadWechatQrCode() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WechatQrCodeApiData info = WechatQrCodeApiData.newInstance();
		info.setStoreId(21L);
		info.setWechatImg("img/store/1/storePayQrCode/2017_08_30_18_a6289c2f-87a4-4c02-ac1c-edf179bdb4d7.jpg");
		StoreMgr.getInstance().uploadWechatQrCode(info);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	private void checkStoreClerkInfo(StoreClerkInfo storeClerkInfo) {
		Assert.assertNotNull("应该有 storeClerkInfo 关联存在", storeClerkInfo);
	}
	
	@Test
	public void testFindByLikeName() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<Store> storeList = StoreMgr.getInstance().findByName("智美通", 20, 1);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		for (Store store : storeList) {
			System.out.println(store.getName());
		}
	}
	
	@Test
	public void testUpdateStore() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623958L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
//		long storeId = 21L;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		Store store = StoreMgr.getInstance().get(storeId);
//		UpdateStoreInfoApiData inputData = UpdateStoreInfoApiData.newInstance();
//		FastBeanCopyer.getInstance().copy(store, inputData);
//		inputData.setWechatRecommendImg("img/store/21/2017_10_23_13_e7db29ae-3a55-42eb-9cb3-f34fa9386e8c.png");
//		inputData.setStoreId(storeId);
//		StoreMgr.getInstance().updateStoreInfo(storeId, inputData);
		
		StoreUpdateStatusData updateStatusData = StoreUpdateStatusData.newInstance();
		updateStatusData.setPassword("123456");
		updateStatusData.setStoreId(15915L);
		updateStatusData.setState(4);
		StoreMgr.getInstance().updateStatus(updateStatusData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testMyStores() {
		//15927
		//http://192.168.40.220/storems/ws/v1/store/findByBuser?pageItemCount=20&pageNo=1&buserId=201607&findType=0&
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		BUser bUser = BUserMgr.getInstance().get(248664L);
//		System.out.println(bUser);
		
		List<Store> storeList = StoreMgr.getInstance().findBUserStores(boss.getId(), 20, 0,0);
		for (Store store : storeList) {
			System.out.println(store.getId()+"----"+store.getName());
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetStore() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(202L);
		System.out.println(store.getName()+"----"+store.getAcodeImg());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
