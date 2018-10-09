package com.hq.chainStore.service.storeCardInfo.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.chainStore.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.chainStore.service.productCardDetail.data.ProductCardDetail;
import com.hq.chainStore.service.productCardDetail.data.ProductCardItem;
import com.hq.chainStore.service.productCardDetail.data.ProductCardItemEnum;
import com.hq.chainStore.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardForm;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.hq.testClass.robot.storeCardInfo.ProductCardDetailRandomData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ProductCardDetailMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		ProductCard productCard = boss.getRandomProductCard(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String id = productCard.getId();
		ProductCardDetail detail1 = ProductCardDetailMgr.getInstance().getProductCardDetail(storeId, id);
		System.out.println(JsonUtil.getInstance().toJson(detail1));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<ProductCardDetail> page = ProductCardDetailMgr.getInstance().getProductCardDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<ProductCardDetail> list = page.getList();
		for (ProductCardDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		AddProductCardForm param = ProductCardDetailRandomData.newInstance(storeCardInfo.getProductCardIndex()+1).toAddProductCardForm();
		List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();
		ProductCardItem item = ProductCardItem.newInstance();
		item.setCount(1);
		item.setDiscountPrice(RandomUtils.nextFloat(100f, 1000f));
		item.setItemType(0);
		item.setPgId("96");
		productCardItems.add(item);
		param.setProductCardItems(productCardItems);
		StoreCardInfoMgr.getInstance().addProductCard(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdate() {
		ProductInfo randomProductInfo = boss.getRandomProductInfo(storeId);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardDetail productCardDetail = ProductCardDetailMgr.getInstance().getProductCardDetail(storeId, "_prd_16052_2");
		UpdProductCardForm param = UpdProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(productCardDetail, param);
		param.setId(productCardDetail.getId());
		param.setName("修改名称");
		
		List<ProductCardItem> productCardItems = productCardDetail.getProductCardItems();
		ProductCardItem item = ProductCardItem.newInstance();
		item.setPgId(randomProductInfo.getId()+"");
		item.setCount(RandomUtils.nextInt(1, 10));
		item.setItemType(ProductCardItemEnum.PRODUCT.ordinal());
		productCardItems.add(item);
		param.setProductCardItems(productCardItems);
		
		StoreCardInfoMgr.getInstance().updProductCard(storeId, param);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
