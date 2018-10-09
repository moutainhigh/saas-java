package com.hq.chainStore.service.storeCardInfo.bs;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.chainStore.service.productCardDetail.data.ProductCardDetail;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.DelProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeAddForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeRemoveForm;
import com.hq.chainStore.service.storeCardInfo.apiData.PrdCardTypeUpdateForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.data.CardStatusEnum;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoSynDataHolder;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.storeCardInfo.StoreCardInfoRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class StoreCardInfoMgrTest {

	private static Boss boss;
	private static long storeId=0L;
	private static List<ProductInfo> products = null;
	
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
		products = StoreProductInfoMgr.getInstance().getProductList(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddPrdCardType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		PrdCardTypeAddForm param = PrdCardTypeAddForm.newInstance();
		param.setIndex(cardInfo.getPrdCardTypeIndex()+1);
		param.setName("次卡分类一");
		StoreCardInfoMgr.getInstance().addPrdCardType(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdatePrdCardType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardTypeUpdateForm param = PrdCardTypeUpdateForm.newInstance();
		param.setId("1");
		param.setName("修改次卡分类");
		StoreCardInfoMgr.getInstance().updPrdCardType(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelPrdCardType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardTypeRemoveForm param = PrdCardTypeRemoveForm.newInstance();
		param.setId("1");
		StoreCardInfoMgr.getInstance().delPrdCardType(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		System.out.println(JsonUtil.getInstance().toJson(cardInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		System.out.println(JsonUtil.getInstance().toJson(cardInfo));
		StoreCardInfo data = StoreCardInfoSynDataHolder.getInstance().getData(boss.getId(), String.valueOf(storeId));
		System.out.println(JsonUtil.getInstance().toJson(data));
		StoreCardInfo cardInfo2 = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		System.out.println(cardInfo2.getMembershipCardMap().size());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testSplitUpdateCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		MembershipCardDetail detail1 = MembershipCardDetailMgr.getInstance().getMembershipCardDetail("_mem_21_55");
//		UpdMembershipCard param = UpdMembershipCard.newInstance();
//		FastBeanCopyer.getInstance().copy(detail1, param);
//		param.setName("会员卡名称修改");
//		param.setDescript("描述-普朗克");
//		StoreCardInfoMgr.getInstance().updMembershipCard(storeId, param);
		
		ProductCardDetail detail2 = ProductCardDetailMgr.getInstance().getProductCardDetail(storeId, "_prd_21_73");
		UpdProductCardForm param = UpdProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(detail2, param);
		param.setName("次卡名称修改111");
		param.setDescript("次卡描述-普朗克111");
		
		Collection<PrdInCard> values = detail2.getProductMap().values();
		for (PrdInCard prdInCard : values) {
			prdInCard.setCount(RandomUtils.nextInt(10, 20));
		}
		param.getProductList().addAll(values);
		
		StoreCardInfoMgr.getInstance().updProductCard(storeId, param);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		StoreCardInfo cardInfo = robot.getStoreCardInfo(storeId);
		System.out.println(cardInfo);
		ProductInfo productInfo = products.get(RandomUtils.nextInt(0, products.size()));
		
		robot.addMembershipCard(storeId, cardInfo.getMembershipCardIndex()+1);
		
		PrdInCard prdInCard = PrdInCard.newInstance();
        prdInCard.setPrdId(Long.valueOf(productInfo.getId()));
		prdInCard.setCount(RandomUtils.nextInt(1, 10));
		prdInCard.setType(RandomUtils.nextInt(0, 2));
		robot.getData().getProductList().clear();
		robot.getData().getProductList().add(prdInCard);
		
		robot.addProductCard(storeId, cardInfo.getProductCardIndex()+1, RandomUtils.nextInt(0, 3));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		StoreCardInfo cardInfo = robot.getStoreCardInfo(storeId);
		
		MembershipCard membershipCard = cardInfo.getMembershipCardMap().get("_mem_21_43");
		membershipCard.setDescript("测试修改功能");
		robot.updMembershipCard(storeId, membershipCard);
		
		ProductCard productCard = cardInfo.getProductCardMap().get("_prd_21_61");
		productCard.setDescript("测试修改功能");
		robot.updProductCard(storeId, productCard);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateCardState() {
		storeId = 14L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		
//		UpdMemberCardStateData updateMemberCardStateData = UpdMemberCardStateData.newInstance();
//		updateMemberCardStateData.setId("_mem_21_43");
//		updateMemberCardStateData.setState(CardStatusEnum.OPEN.ordinal());
//		updateMemberCardStateData.setStoreId(storeId);
//		robot.updMemberCardState(storeId, updateMemberCardStateData);
		
		UpdProductCardStateData updateProductCardStateData = UpdProductCardStateData.newInstance();
		updateProductCardStateData.setId("14__cpci_1_2 ");
		updateProductCardStateData.setState(CardStatusEnum.OPEN.ordinal());
		updateProductCardStateData.setStoreId(storeId);
		robot.updProductCardState(storeId, updateProductCardStateData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testBatchUpdateCardState() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		
		BatchUpdMemberCardStateData batchUpdateMemberCardStateData = BatchUpdMemberCardStateData.newInstance();
		batchUpdateMemberCardStateData.getMbCardIdSet().add("_mem_21_43");
		batchUpdateMemberCardStateData.getMbCardIdSet().add("_mem_21_44");
		batchUpdateMemberCardStateData.setState(CardStatusEnum.OPEN.ordinal());
		batchUpdateMemberCardStateData.setStoreId(storeId);
		robot.batchUpdMemberCardState(storeId, batchUpdateMemberCardStateData);
		
		BatchUpdProductCardStateData batchUpdateProductCardStateData = BatchUpdProductCardStateData.newInstance();
		batchUpdateProductCardStateData.getPrdCardIdSet().add("_prd_21_61");
		batchUpdateProductCardStateData.getPrdCardIdSet().add("_prd_21_62");
		batchUpdateProductCardStateData.setState(CardStatusEnum.OPEN.ordinal());
		batchUpdateProductCardStateData.setStoreId(storeId);
		robot.batchUpdProductCardState(storeId, batchUpdateProductCardStateData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		
		robot.delMembershipCard(storeId, "_mem_21_43");
		robot.delProductCard(storeId, "_prd_21_61");
		
		//cardNumber6426
//		StoreCardInfo storeCardInfo = robot.getStoreCardInfo(storeId);
//		robot.getData().setNumber("cardNumber6426");
//		robot.addProductCard(storeId, storeCardInfo.getProductCardIndex()+1, 0);
		
//		robot.getData().setNumber("cardNumber7676");
//		robot.addMembershipCard(storeId, storeCardInfo.getMembershipCardIndex()+1);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelCard2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
//		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getStoreCardInfo(storeId);
//		Collection<ProductCard> values = storeCardInfo.getProductCardMap().values();
//		for (ProductCard productCard : values) {
//			if(productCard.getEntityState() != EntityState.Deleted.ordinal()) {
//				System.out.println(JsonUtil.getInstance().toJson(productCard));
//			}
//		}
		
		DelProductCardForm param = DelProductCardForm.newInstance();
		param.setId("_prd_4_6");
		StoreCardInfoMgr.getInstance().delProductCard(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
