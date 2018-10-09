package com.hq.chainStore.service.storeProductInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.chainStore.service.storeProductInfo.apiData.CancelProductFromTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductTypeData;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductType;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoMgr {

	public static StoreProductInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreProductInfoMgr.class);
	}
	
	public StoreProductInfo findSimpleStoreInfo(long storeId) {
		return StoreProductInfoDAO.getInstance().getSimple(storeId);
	}

	@Deprecated
	public StoreProductInfo get(long id) {
		return StoreProductInfoDAO.getInstance().getDetail(id);
	}
	
	public void update(long id, StoreProductInfoUpdateForm updateForm) {
		StoreProductInfoDAO.getInstance().update(id, updateForm);
	}
	
	public void addProductType(long storeId, AddProductTypeData addProductTypeData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setAddProductTypeData(addProductTypeData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductType);
		update(storeId, updateForm);
	}
	
	public void removeProductType(long storeId, RemoveProductTypeData removeProductTypeData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setRemoveProductTypeData(removeProductTypeData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.RemoveProductType);
		update(storeId, updateForm);
	}
	
	public void updateProductType(long storeId, UpdateProductTypeData updateProductTypeData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductTypeData(updateProductTypeData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductType);
		update(storeId, updateForm);
	}
	
	public void addProductInfo(long storeId, AddProductInfoData addProductInfoData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setAddProductInfoData(addProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductInfo);
		update(storeId, updateForm);
	}
	
	public void updateProductInfo(long storeId, UpdateProductInfoData updateProductInfoData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductInfoData(updateProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductInfo);
		update(storeId, updateForm);
	}
	
	public void updateProductState(long storeId, UpdateProductStateData updateProductStateData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductStateData(updateProductStateData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductState);
		update(storeId, updateForm);
	}
	
	public void batchUpdateProductState(long storeId, BatchUpdateProductStateData batchUpdateProductStateData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setBatchUpdateProductStateData(batchUpdateProductStateData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.BatchUpdateProductState);
		update(storeId, updateForm);
	}
	
	public void removeProductInfo(long storeId, RemoveProductInfoData removeProductInfoData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setRemoveProductInfoData(removeProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.RemoveProductInfo);
		update(storeId, updateForm);
	}
	
	public void addProductToTop(long storeId, AddProductToTopData addProductToTopData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setAddProductToTopData(addProductToTopData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductToTop);
		update(storeId, updateForm);
	}
	
	public void cancelProductFromTop(long storeId, CancelProductFromTopData cancelProductFromTopData){
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setCancelProductFromTopData(cancelProductFromTopData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.CancelProductFromTop);
		update(storeId, updateForm);
	}
	
	
	/***********************productInfo*************************/

	public ProductInfo getProduct(long id,String name) {
		ProductInfo product = null;
		StoreProductInfo storeProductInfo = StoreProductInfoDAO.getInstance().get(id);
		if(storeProductInfo != null){
			Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
			for (String key : productInfoMap.keySet()) {
				if(productInfoMap.get(key).getName().equals(name)){
					product = productInfoMap.get(key);
				}
			}
		}
		return product;
	}
	
	public List<ProductInfo> getProductList(long id) {
		List<ProductInfo> productList = null;
		StoreProductInfo storeProductInfo = StoreProductInfoDAO.getInstance().get(id);
		if(storeProductInfo != null){
			Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
			Collection<ProductInfo> collection = productInfoMap.values();
			productList = new ArrayList<ProductInfo>(collection);
		}
		return productList;
	}
	
	/***********************productType*************************/

	public ProductType getProductType(long id,String name) {
		ProductType prdType = null;
		StoreProductInfo storeProductInfo = StoreProductInfoDAO.getInstance().get(id);
		if(storeProductInfo != null){
			Map<String, ProductType> productTypeMap = storeProductInfo.getProductTypeMap();
			for (String key : productTypeMap.keySet()) {
				if(productTypeMap.get(key).getName().equals(name)){
					prdType = productTypeMap.get(key);
				}
			}
		}
		return prdType;
	}
	
	public List<ProductType> getPrdTypeList(long id) {
		List<ProductType> prdTypeList = null;
		StoreProductInfo storeProductType = StoreProductInfoDAO.getInstance().get(id);
		if(storeProductType != null){
			Map<String, ProductType> productTypeMap = storeProductType.getProductTypeMap();
			Collection<ProductType> collection = productTypeMap.values();
			prdTypeList = new ArrayList<ProductType>(collection);
		}
		return prdTypeList;
	}
	
	
}
