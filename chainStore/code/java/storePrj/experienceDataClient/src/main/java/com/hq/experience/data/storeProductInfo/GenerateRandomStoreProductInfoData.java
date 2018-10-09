package com.hq.experience.data.storeProductInfo;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfoState;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.experienceData.TProduct;
import com.hq.experienceData.TProductType;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

public class GenerateRandomStoreProductInfoData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.169:9110/storems/ws/v1";
		String reportService = "http://192.168.10.169:9110/storereportms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateRandomStoreProductInfoData().genData(phone);
	}
	
	public GenerateRandomStoreProductInfoData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genProductInfoData();
			}
			System.out.println("Generate ProductInfo Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genProductInfoData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		List<TProduct> pList = TProduct.buildRandomTProductList(50);

		for (TProduct tProduct : pList) {
			StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
			int productIdIndex = storeProductInfo.getProductIdIndex();
			StoreProductInfoUpdateForm storeProductInfoUpdateForm = StoreProductInfoUpdateForm.newInstance();
			AddProductInfoData addProductInfoData = AddProductInfoData.newInstance();
			addProductInfoData.setIndex(productIdIndex+1);
			addProductInfoData.setName(tProduct.getName()+RandomUtils.nextLong(100L, 999L));
			addProductInfoData.setPrice(tProduct.getPrice());
			addProductInfoData.setDescript(tProduct.getDescription());
			addProductInfoData.setState(ProductInfoState.Open.ordinal());
			addProductInfoData.setStoreId(storeId);
			
			storeProductInfoUpdateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductInfo);
			storeProductInfoUpdateForm.setAddProductInfoData(addProductInfoData);
			StoreProductInfoMgr.getInstance().update(storeId, storeProductInfoUpdateForm);
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	
	public void genProductTypeData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<TProductType> pList = TProductType.buildTProductTypeList();
		for (TProductType tProductType : pList) {
			StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
			int productTypeIdIndex = storeProductInfo.getProductTypeIdIndex();
			StoreProductInfoUpdateForm storeProductInfoUpdateForm = StoreProductInfoUpdateForm.newInstance();
			AddProductTypeData addProductTypeData = AddProductTypeData.newInstance();
			addProductTypeData.setIndex(productTypeIdIndex+1);
			addProductTypeData.setName(tProductType.getName()+RandomUtils.nextLong(100L, 999L));
			addProductTypeData.setStoreId(storeId);
			

			storeProductInfoUpdateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductType);
			storeProductInfoUpdateForm.setAddProductTypeData(addProductTypeData);
			StoreProductInfoMgr.getInstance().update(storeId, storeProductInfoUpdateForm);
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
