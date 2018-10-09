package com.hq.chainStore.service.beauticianProduct.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.beauticianProduct.data.BeauticianProduct;
import com.hq.chainStore.service.beauticianProduct.data.BeauticianProductSynDataHolder;
import com.hq.chainStore.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class BeauticianProductMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	private static List<String> productIds = new ArrayList<String>();
	private static List<Long> beauticianIds = new ArrayList<Long>();
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo info = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		for (ProductInfo productInfo : info.getProductInfoMap().values()) {
			productIds.add(productInfo.getId());
		}
		Assert.assertFalse("应该非空", productIds.isEmpty());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreBeauticianInfo info2 = StoreBeauticianInfoMgr.getInstance().get(storeId);
		for (BeauticianInfo beauticianInfo : info2.getBeauticianInfoMap().values()) {
			beauticianIds.add(beauticianInfo.getBuserId());
		}
		Assert.assertFalse("应该非空", beauticianIds.isEmpty());
	}
	
	@Test
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		String beauticianProductId = storeId+"_"+beauticianIds.get(RandomUtils.nextInt(0, beauticianIds.size()));
		String beauticianProductId = storeId+"_54";
		
//		AddForemost addForemost = AddForemost.newInstance();
//		addForemost.setProductId(productIds.get(RandomUtils.nextInt(0, productIds.size())));
//		BeauticianProductMgr.getInstance().addForemost(beauticianProductId, addForemost);
//		BeauticianProduct beauticianProduct = BeauticianProductMgr.getInstance().getBeauticianProduct(beauticianProductId);
//		System.out.println(beauticianProduct);
		
//		RemoveForemost removeForemost = RemoveForemost.newInstance();
//		removeForemost.setProductId(11L);
//		BeauticianProductMgr.getInstance().removeForemost(beauticianProductId, removeForemost);
		
		BeauticianProduct beauticianProduct = BeauticianProductSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(beauticianProductId));
		System.out.println(beauticianProduct);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
