package com.hq.chainStore.service.storeProductInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.hq.chainStore.service.productDetail.data.ProductDetail;
import com.hq.chainStore.service.productDetail.data.ProductDetailDataHolder;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.chainStore.service.storeProductInfo.apiData.CancelProductFromTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductTypeData;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfoState;
import com.hq.chainStore.service.storeProductInfo.data.ProductType;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.storeProductInfo.robot.StoreProductRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class StoreProductInfoMgrTest {
	private static Boss boss=null;
	private static long storeId = 0L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	private ProductType getRondomType(StoreProductInfo storeProductInfo) {
		Collection<ProductType> values = storeProductInfo.getProductTypeMap().values();
		List<ProductType> list = new ArrayList<ProductType>(values);
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	@Test
	public void testAddProductInfo(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		ProductType productType = getRondomType(storeProductInfo);
		StoreProductRobotData robotData = StoreProductRobotData.newInstance(RandomUtils.nextInt(10, 100));
		AddProductInfoData addProductInfoData = AddProductInfoData.newInstance();
		FastBeanCopyer.getInstance().copy(robotData, addProductInfoData);
		addProductInfoData.setIndex(storeProductInfo.getProductIdIndex()+1);
		addProductInfoData.getImgPathList().add(robotData.getPath());
		addProductInfoData.setTypeId(productType.getId());
		addProductInfoData.setStoreId(storeId);
		StoreProductInfoMgr.getInstance().addProductInfo(storeId, addProductInfoData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveProductInfo(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		RemoveProductInfoData removeProductInfoData = RemoveProductInfoData.newInstance();
		removeProductInfoData.setStoreId(storeId);
		removeProductInfoData.setId("99");
		StoreProductInfoMgr.getInstance().removeProductInfo(storeId, removeProductInfoData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProductInfo(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductDetail productInfo = ProductDetailDataHolder.getInstance().getData(boss.getId(), "101", storeId);
		UpdateProductInfoData updateProductInfoData = UpdateProductInfoData.newInstance();
		BeanUtils.copyProperties(productInfo, updateProductInfoData, "id");
		updateProductInfoData.setId(productInfo.getProductId());
		updateProductInfoData.setName("项目修改名称");
		updateProductInfoData.setDescript("rrrrrrrrr");
		updateProductInfoData.setStoreId(storeId);
		StoreProductInfoMgr.getInstance().updateProductInfo(storeId, updateProductInfoData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProductState(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		ProductInfo productInfo = storeProductInfo.getProductInfoMap().get("93");
		UpdateProductStateData updateProductStateData = UpdateProductStateData.newInstance();
		updateProductStateData.setId(productInfo.getId());
		updateProductStateData.setStoreId(storeId);
		updateProductStateData.setState(ProductInfoState.Open.ordinal());
		StoreProductInfoMgr.getInstance().updateProductState(storeId, updateProductStateData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testBatchUpdateProductState(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		BatchUpdateProductStateData batchUpdateProductStateData = BatchUpdateProductStateData.newInstance();
		batchUpdateProductStateData.setState(ProductInfoState.Close.ordinal());
		batchUpdateProductStateData.setStoreId(storeId);
		Set<String> prdIdSet = new HashSet<String>();
		prdIdSet.add("93");
		prdIdSet.add("94");
		batchUpdateProductStateData.setPrdIdSet(prdIdSet);
		StoreProductInfoMgr.getInstance().batchUpdateProductState(storeId, batchUpdateProductStateData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddProductType(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		AddProductTypeData addProductTypeData = AddProductTypeData.newInstance();
		addProductTypeData.setIndex(storeProductInfo.getProductTypeIdIndex()+1);
		addProductTypeData.setStoreId(storeId);
		addProductTypeData.setName("项目分类-61");//+RandomUtils.nextInt(1, 100)
		StoreProductInfoMgr.getInstance().addProductType(storeId, addProductTypeData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveProductType(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().get(storeId);
//		Collection<ProductType> values = storeProductInfo.getProductTypeMap().values();
//		for (ProductType productType : values) {
//			RemoveProductTypeData removeProductTypeData = RemoveProductTypeData.newInstance();
//			removeProductTypeData.setId(productType.getId());
//			removeProductTypeData.setStoreId(storeId);
//			StoreProductInfoMgr.getInstance().removeProductType(storeId, removeProductTypeData);
//		}
		RemoveProductTypeData removeProductTypeData = RemoveProductTypeData.newInstance();
		removeProductTypeData.setId("16");
		removeProductTypeData.setStoreId(storeId);
		StoreProductInfoMgr.getInstance().removeProductType(storeId, removeProductTypeData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProductType(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		ProductType productType = storeProductInfo.getProductTypeMap().get("16");
		UpdateProductTypeData updateProductTypeData = UpdateProductTypeData.newInstance();
		updateProductTypeData.setId(productType.getId());
		updateProductTypeData.setStoreId(storeId);
		updateProductTypeData.setName("项目分类-"+RandomUtils.nextInt(1, 100));
		StoreProductInfoMgr.getInstance().updateProductType(storeId, updateProductTypeData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testCancelProductFromTop(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		CancelProductFromTopData cancelProductFromTopData = CancelProductFromTopData.newInstance();
		cancelProductFromTopData.setProductId("9");
		cancelProductFromTopData.setStoreId(storeId);
		StoreProductInfoMgr.getInstance().cancelProductFromTop(storeId, cancelProductFromTopData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddProductToTop(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AddProductToTopData addProductToTopData = AddProductToTopData.newInstance();
		addProductToTopData.setProductId("92");
		addProductToTopData.setStoreId(storeId);
		StoreProductInfoMgr.getInstance().addProductToTop(storeId, addProductToTopData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		System.out.println(storeData.getSplitMark());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHolder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeData = StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(storeData.getSplitMark());
		StoreProductInfo storeData2 = StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(storeData2.getSplitMark());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	

}
