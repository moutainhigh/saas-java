package com.hq.chainClient.service.chainCard.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chain.data.ChainStore;
import com.hq.chainClient.service.chainCard.apiData.AddMembershipCard;
import com.hq.chainClient.service.chainCard.apiData.AddProductCardForm;
import com.hq.chainClient.service.chainCard.apiData.MemberCardAllotForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeAddForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeRemoveForm;
import com.hq.chainClient.service.chainCard.apiData.PrdCardTypeUpdateForm;
import com.hq.chainClient.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCard;
import com.hq.chainClient.service.chainCard.data.PrdCardType;
import com.hq.chainClient.service.chainCard.data.ProductCard;
import com.hq.chainClient.service.chainCard.data.ProductCardItem;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chainCard.MemberCardRobotData;
import com.hq.chainClient.testClass.robot.chainCard.ProductCardRobotData;

public class ChainCardClientMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}
	
	@Test
	public void testAddPrdCardType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainCard chainCard = ChainCardClientMgr.getInstance().get(chainId);
		PrdCardTypeAddForm param = PrdCardTypeAddForm.newInstance();
		param.setIndex(chainCard.getPrdCardTypeIndex()+1);
		param.setName("修改次卡分类"+RandomUtils.nextInt(1, 100));
		ChainCardClientMgr.getInstance().addPrdCardType(chainId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdatePrdCardType() {
		PrdCardType type = boss.getRandomProductCardType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardTypeUpdateForm param = PrdCardTypeUpdateForm.newInstance();
		param.setId(type.getId());
		param.setName("修改次卡分类");
		ChainCardClientMgr.getInstance().updPrdCardType(chainId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelPrdCardType() {
		PrdCardType type = boss.getRandomProductCardType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PrdCardTypeRemoveForm param = PrdCardTypeRemoveForm.newInstance();
		param.setId(type.getId());
		ChainCardClientMgr.getInstance().delPrdCardType(chainId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddProductCard() {
		PrdCardType type = boss.getRandomProductCardType(chainId);
		List<ProductCardItem> productCardItems = getProductCardItems();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainCard chainCard = ChainCardClientMgr.getInstance().get(chainId);
		AddProductCardForm inputForm = ProductCardRobotData.newRandomInstance().toAddProductCardForm(chainCard.getProductCardIndex()+1, type.getId());
		inputForm.setProductCardItems(productCardItems);
		ChainCardClientMgr.getInstance().addProductCard(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private List<ProductCardItem> getProductCardItems(){
		List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();
		ProductCardItem item = ProductCardItem.newInstance();
		item.setCount(RandomUtils.nextInt(1, 10));
		item.setDiscountPrice(RandomUtils.nextFloat(100f, 500f));
		item.setItemType(RandomUtils.nextInt(0, 3));
		if(item.getItemType() == 0) {
			item.setPgId(boss.getRandomProduct(chainId).getId());
		}else if(item.getItemType() == 1) {
			item.setPgId(boss.getRandomGoods(chainId).getId());
		}else if(item.getItemType() == 2) {
			item.setPgId(boss.getRandomPackageProject(chainId).getId());
		}
		productCardItems.add(item);
		return productCardItems;
	}
	
	@Test
	public void testAllotProductCard() {
		Set<Long> applyStoreIds = getApplyStoreIds();
		ProductCard data = boss.getRandomProductCard(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardAllotForm inputForm = ProductCardAllotForm.newInstance();
		inputForm.setId(data.getId());
		inputForm.setApplyStoreIds(applyStoreIds);
		ChainCardClientMgr.getInstance().allotProductCard(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddMembershipCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainCard chainCard = ChainCardClientMgr.getInstance().get(chainId);
		AddMembershipCard inputForm = MemberCardRobotData.newRandomInstance().toAddMembershipCard(chainCard.getMembershipCardIndex()+1);
		ChainCardClientMgr.getInstance().addMembershipCard(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAllotMemberCard() {
		Set<Long> applyStoreIds = getApplyStoreIds();
		MembershipCard data = boss.getRandomMembershipCard(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MemberCardAllotForm inputForm = MemberCardAllotForm.newInstance();
		inputForm.setId(data.getId());
		inputForm.setApplyStoreIds(applyStoreIds);
		ChainCardClientMgr.getInstance().allotMemberCard(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private Set<Long> getApplyStoreIds(){
		Set<Long> applyStoreIds = new HashSet<Long>();
		ChainStore chainStore = boss.getRandomChainStore(chainId);
		applyStoreIds.add(chainStore.getStoreId());
		return applyStoreIds;
	}
	
}
