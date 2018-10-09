package com.hq.chainClient.service.chainProduct.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chainProduct.apiData.AddProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.AddProductTypeData;
import com.hq.chainClient.service.chainProduct.apiData.RemoveProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.RemoveProductTypeData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductInfoData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainClient.service.chainProduct.apiData.UpdateProductTypeData;
import com.hq.chainClient.service.chainProduct.bs.ChainProductClientMgr;
import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.Product;
import com.hq.chainClient.service.chainProduct.data.ProductStateEnum;
import com.hq.chainClient.service.chainProduct.data.ProductType;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chainProduct.ChainProductRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChainProductMgrTest {
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
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainProduct chainData = ChainProductClientMgr.getInstance().get(chainId);
		System.out.println(JsonUtil.getInstance().toJson(chainData));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddProductType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainProduct chainData = ChainProductClientMgr.getInstance().get(chainId);
		AddProductTypeData inputForm = AddProductTypeData.newInstance();
		inputForm.setIndex(chainData.getProductTypeIdIndex()+1);
		inputForm.setName("项目分类"+RandomUtils.nextInt(1, 100));
		ChainProductClientMgr.getInstance().addProductType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProductType() {
		ProductType productType = boss.getRandomProductType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		UpdateProductTypeData inputForm = UpdateProductTypeData.newInstance();
		FastBeanCopyer.getInstance().copy(productType, inputForm);
		inputForm.setName("修改项目分类");
		ChainProductClientMgr.getInstance().updateProductType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveProductType() {
		ProductType productType = boss.getRandomProductType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		RemoveProductTypeData inputForm = RemoveProductTypeData.newInstance();
		inputForm.setId(productType.getId());
		ChainProductClientMgr.getInstance().removeProductType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddProduct() {
		ProductType type = boss.getRandomProductType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainProduct chainData = ChainProductClientMgr.getInstance().get(chainId);
		AddProductInfoData inputForm = ChainProductRobotData.newRandomInstance().toAddProductInfoData(chainData.getProductIdIndex()+1, type.getId());
		ChainProductClientMgr.getInstance().addProductInfo(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProduct() {
		Product Product = boss.getRandomProduct(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		UpdateProductInfoData inputForm = UpdateProductInfoData.newInstance();
		FastBeanCopyer.getInstance().copy(Product, inputForm);
		inputForm.setName("傻姑娘");
		ChainProductClientMgr.getInstance().updateProductInfo(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveProduct() {
		Product Product = boss.getRandomProduct(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		RemoveProductInfoData inputForm = RemoveProductInfoData.newInstance();
		inputForm.setId(Product.getId());
		ChainProductClientMgr.getInstance().removeProductInfo(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateProductState() {
		Product Product = boss.getRandomProduct(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		UpdateProductStateData inputForm = UpdateProductStateData.newInstance();
		inputForm.setId(Product.getId());
		inputForm.setState(ProductStateEnum.Close.ordinal());
		ChainProductClientMgr.getInstance().updateProductState(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
}
