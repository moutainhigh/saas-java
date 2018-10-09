package com.hq.storeClient.service.storeProductInfo.bs;

import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.storeClient.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.storeClient.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.storeClient.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeClient.service.storeProductInfo.data.ProductInfo;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreProductInfoClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		StoreProductInfo data = StoreProductInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}

	@Ignore
	@Test
	public void testRemoveAll() {
		long storeId = 410L;
		
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreProductInfo data = StoreProductInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(data.getProductInfoMap()));
		ValidateThreadLocal.getInstance().remove();
		
		Collection<ProductInfo> values = data.getProductInfoMap().values();
		for (ProductInfo productInfo : values) {
			remove(storeId, productInfo);
			System.out.println(JsonUtil.getInstance().toJson(productInfo));
		}
//		ProductInfo productInfo = data.getProductInfoMap().get("54");
//		update(storeId, productInfo);
//		System.out.println(JsonUtil.getInstance().toJson(productInfo));
	}
	
	private void remove(long storeId, ProductInfo productInfo) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		RemoveProductInfoData removeForm = RemoveProductInfoData.newInstance();
		removeForm.setId(productInfo.getId());
		removeForm.setStoreId(storeId);
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreProductInfoUpdateType.RemoveProductInfo.ordinal());
		updateForm.setRemoveProductInfoData(removeForm);
		StoreProductInfoClientMgr.getInstance().update(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testUpdateInfo() {
		long storeId = 16052L;
		String productId = "1";
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setStoreId(storeId);
		
		//促销
//		ProductDetail detail = ProductDetailClientMgr.getInstance().getProductDetail(storeId, productId);
//		UpdateProductInfoData updateProductInfoData = UpdateProductInfoData.newInstance();
//		FastBeanCopyer.getInstance().copy(detail, updateProductInfoData);
//		updateProductInfoData.setId(productId);
//		updateProductInfoData.setPromotionFlag(PromotionFlagEnum.Yes.ordinal());
//		updateProductInfoData.setPromotionPrice(RandomUtils.nextFloat(100f, 500f));
//		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductInfo);
//		updateForm.setUpdateProductInfoData(updateProductInfoData);
		
		//项目置顶
		AddProductToTopData addProductToTopData = AddProductToTopData.newInstance();
		addProductToTopData.setProductId(productId);
		addProductToTopData.setStoreId(storeId);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductToTop);
		updateForm.setAddProductToTopData(addProductToTopData);
		
//		CancelProductFromTopData cancelProductFromTopData = CancelProductFromTopData.newInstance();
//		cancelProductFromTopData.setProductId(productId);
//		cancelProductFromTopData.setStoreId(storeId);
//		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.CancelProductFromTop);
//		updateForm.setCancelProductFromTopData(cancelProductFromTopData);
		
		StoreProductInfoClientMgr.getInstance().update(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testBatchUpdateProductState() {
		fail("Not yet implemented");
	}

}
