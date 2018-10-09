package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.order.data.BuyTypeEnum;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddByAppoint;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddByLeaguer;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataDeleteForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataSwitchLeaguer;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateBuyItemsForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateInfoForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateStatusForm;
import com.hq.chainStore.service.workFlowData.apiData.save.BuyItemSaveForm;
import com.hq.chainStore.service.workFlowData.apiData.save.LeaguerSaveForm;
import com.hq.chainStore.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataStatusEnum;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataMgrTest {
	private static Boss boss;
	private static long storeId=0L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataAddForm addForm = WorkFlowDataAddForm.newInstance();
		addForm.setBuserId(boss.getId());
		addForm.setStoreId(storeId);
		addForm.setWorkFlowTypeId(2L);
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().addWorkFlowData(addForm);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		
		WorkFlowDataUpdateInfoForm updateInfoForm = WorkFlowDataUpdateInfoForm.newInstance();
		updateInfoForm.setWorkFlowTypeId(3L);
		updateInfoForm.setLeaguerInfoComment("LeaguerInfoComment-"+RandomUtils.nextInt(0, 100));
		WorkFlowDataMgr.getInstance().updWorkFlowDataInfo(workFlowData.getId(), updateInfoForm);
		
		WorkFlowDataUpdateStatusForm updateStatusForm = WorkFlowDataUpdateStatusForm.newInstance();
		updateStatusForm.setStatus(WorkFlowDataStatusEnum.COMPLETE.ordinal());
		WorkFlowDataMgr.getInstance().updWorkFlowDataStatus(workFlowData.getId(), updateStatusForm);
		
		WorkFlowData workFlowData2 = WorkFlowDataMgr.getInstance().getWorkFlowData(workFlowData.getId());
		System.out.println(JsonUtil.getInstance().toJson(workFlowData2));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataAddForm addForm = WorkFlowDataAddForm.newInstance();
		addForm.setBuserId(boss.getId());
		addForm.setStoreId(storeId);
		addForm.setWorkFlowTypeId(2L);
		WorkFlowDataMgr.getInstance().addWorkFlowData(addForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataMgr.getInstance().deleteWorkFlowData(1L);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testDelete2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataDeleteForm workFlowDataDeleteForm = WorkFlowDataDeleteForm.newInstance();
		workFlowDataDeleteForm.setWorkFlowDataId(6777L);
		WorkFlowDataMgr.getInstance().deleteWorkFlowData(6777L, workFlowDataDeleteForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddByAppoint() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataAddByAppoint addForm = WorkFlowDataAddByAppoint.newInstance();
		addForm.setAppointmentId(4760L);
		addForm.setCreatorId(boss.getId());
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().addByAppoint(addForm);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddByLeaguer() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataAddByLeaguer addForm = WorkFlowDataAddByLeaguer.newInstance();
		addForm.setCreatorId(boss.getId());
		addForm.setStoreId(storeId);
		addForm.setLeaguerId("16052_67194");
		addForm.setLeaguerName("客户-9352");
		addForm.setWorkFlowTypeId(8L);
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().addByLeaguer(addForm);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdateInfo() {
		long workFlowDataId=4826L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		Set<Long> buserIds = new HashSet<Long>();
//		buserIds.add(4L);
//		buserIds.add(5L);
		
//		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().getWorkFlowData(workFlowDataId);
//		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		
//		WorkFlowDataUpdateInfoForm updateInfoForm = WorkFlowDataUpdateInfoForm.newInstance();
//		updateInfoForm.setWorkFlowTypeId(3L);
//		updateInfoForm.setLeaguerInfoComment("LeaguerInfoComment"+RandomUtils.nextInt(0, 100));
//		WorkFlowDataMgr.getInstance().updWorkFlowDataInfo(workFlowDataId, updateInfoForm);
		
//		WorkFlowDataUpdateStatusForm updateStatusForm = WorkFlowDataUpdateStatusForm.newInstance();
//		updateStatusForm.setStatus(WorkFlowDataStatusEnum.COMPLETE.ordinal());
//		WorkFlowDataMgr.getInstance().updWorkFlowDataStatus(workFlowDataId, updateStatusForm);
		
		WorkFlowDataUpdateBuyItemsForm buyItemsForm = WorkFlowDataUpdateBuyItemsForm.newInstance();
//		FastBeanCopyer.getInstance().copy(workFlowData, buyItemsForm);
//		
//		Map<String, DecreasePrdCardRecord> decreasePrdCardRecordMap = buyItemsForm.getDecreasePrdCardRecordMap();
//		Map<String, GoodsRecord> goodsRecordMap = buyItemsForm.getGoodsRecordMap();
//		Map<String, PrdCardRecord> prdCardRecordMap = buyItemsForm.getPrdCardRecordMap();
//		Map<String, ProdRecord> prodRecordMap = buyItemsForm.getProdRecordMap();
//		
//		DecreasePrdCardRecord decreasePrdCardRecord = decreasePrdCardRecordMap.get("0_27_13_45");
//		decreasePrdCardRecord.setDiscount(RandomUtils.nextFloat(10f, 20f));
//		decreasePrdCardRecord.setBuserIds(buserIds);
//		GoodsRecord goodsRecord = goodsRecordMap.get("11");
//		goodsRecord.setDiscount(RandomUtils.nextFloat(10f, 20f));
//		goodsRecord.setBuserIds(buserIds);
//		PrdCardRecord prdCardRecord = prdCardRecordMap.get("1");
//		prdCardRecord.setDiscount(RandomUtils.nextFloat(10f, 20f));
//		prdCardRecord.setBuserIds(buserIds);
//		ProdRecord prodRecord = prodRecordMap.get("66");
//		prodRecord.setDiscount(RandomUtils.nextFloat(10f, 20f));
//		prodRecord.setBuserIds(buserIds);
		
		WorkFlowDataMgr.getInstance().updateWorkFlowDataBuyItems(workFlowDataId, buyItemsForm);
		
		WorkFlowData workFlowData2 = WorkFlowDataMgr.getInstance().getWorkFlowData(workFlowDataId);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		long workFlowDataId=292L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().getWorkFlowData(workFlowDataId);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		long maxTime = System.currentTimeMillis();
		long minTime = maxTime - 100L*24*3600*1000;
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		queryForm.setStoreId(storeId);
		queryForm.setPageItemCount(10);
		queryForm.setPageNo(1);
//		queryForm.setBuserId(121L);
		List<WorkFlowData> list = WorkFlowDataMgr.getInstance().findByCond(queryForm);
		System.out.println(list.size());
		System.out.println(JsonUtil.getInstance().toJson(list));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testPage() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setPageItemCount(1000);
		queryForm.setPageNo(1);
		queryForm.setLeaguerNameOrPhone("客户");
		long startTime = System.currentTimeMillis();
		PageResp<WorkFlowData> pageResp = WorkFlowDataMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(JsonUtil.getInstance().toJson(pageResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testSwitchLeaguer() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataSwitchLeaguer inputForm = WorkFlowDataSwitchLeaguer.newInstance();
		inputForm.setLeaguerId("21_67136");
		inputForm.setWorkFlowDataId(4826L);
		WorkFlowDataMgr.getInstance().switchLeaguer(inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
//	@Ignore
	@Test
	public void testSaveOrUpdate() {
		Leaguer randomLeaguer = boss.getRandomLeaguer(storeId);
		ProductInfo randomProductInfo = boss.getRandomProductInfo(storeId);
//		ClerkInfo clerkInfo = boss.getRandomClerk(storeId);
//		ProductCard randomProductCard = boss.getRandomProductCard(storeId);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		WorkFlowDataSaveForm inputForm = WorkFlowDataSaveForm.newInstance();
		inputForm.setStoreId(storeId);
//		inputForm.setId(7759L);
		
		LeaguerSaveForm leaguerForm = LeaguerSaveForm.newInstance();
		leaguerForm.setFollowUserId(boss.getId());
		leaguerForm.setLeaguerId(randomLeaguer.getId());
		inputForm.setLeaguerSaveForm(leaguerForm);
		
		List<BuyItemSaveForm> buyItemSaveForms = new ArrayList<BuyItemSaveForm>();
		//项目
		BuyItemSaveForm itemForm = BuyItemSaveForm.newInstance();
		itemForm.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
		itemForm.setCount(RandomUtils.nextInt(1, 10));
		itemForm.setDiscount(8.0f);
		itemForm.setOldPrice(randomProductInfo.getPrice());
		itemForm.setPgId(randomProductInfo.getId()+"");
		itemForm.setRestCount(RandomUtils.nextInt(1, itemForm.getCount()));
		buyItemSaveForms.add(itemForm);
		inputForm.setBuyItemSaveForms(buyItemSaveForms);
		//次卡
//		BuyItemSaveForm itemForm = BuyItemSaveForm.newInstance();
//		itemForm.setBuyType(BuyTypeEnum.PRDCARD.ordinal());
//		itemForm.setCount(RandomUtils.nextInt(1, 10));
//		itemForm.setDiscount(8.0f);
//		itemForm.setOldPrice(randomProductCard.getSellPrice());
//		itemForm.setPgId(randomProductCard.getId()+"");
//		buyItemSaveForms.add(itemForm);
//		inputForm.setBuyItemSaveForms(buyItemSaveForms);
		
		//提成
//		List<BonusInfoSaveForm> bonusInfoSaveForms = new ArrayList<BonusInfoSaveForm>();
//		BonusInfoSaveForm bonusInfoSaveForm = BonusInfoSaveForm.newInstance();
//		bonusInfoSaveForm.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
//		bonusInfoSaveForm.setPgId(randomProductInfo.getId()+"");
//		bonusInfoSaveForm.setPrdCardPayType(PrdCardPayEnum.CashPay.ordinal());
//		bonusInfoSaveForm.setProductCardId("");
//		Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();
//		UserBonus userBonus = UserBonus.newInstance();
//		userBonus.setAmount(RandomUtils.nextFloat(100f, 200f));
//		userBonus.setBonusType(BonusTypeEnum.FixedBonus.ordinal());
//		userBonus.setBuserId(clerkInfo.getBuserId());
//		userBonus.setCost(RandomUtils.nextFloat(100f, 200f));
//		userBonus.setPercentage(0.0f);
//		userBonusMap.put(userBonus.getBuserId(), userBonus);
//		bonusInfoSaveForm.setUserBonusMap(userBonusMap);
//		bonusInfoSaveForms.add(bonusInfoSaveForm);
//		inputForm.setBonusInfoSaveForms(bonusInfoSaveForms);
		
		WorkFlowData data = WorkFlowDataMgr.getInstance().saveOrUpdate(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(data.getId()));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
