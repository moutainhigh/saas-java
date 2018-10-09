package com.hq.storeMS.service.storeProductInfo.data;

import java.util.Map;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductTypeData;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoBeanHelper {

	public static StoreProductInfoBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoBeanHelper.class);
	}

	/********************************** 项目Bean基本操作 **********************************/
	public boolean addProductInfo(StoreProductInfo storeProductInfo, AddProductInfoData addProductInfoData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		ProductInfo productInfo = addProductInfoData.toProductInfo();
		Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
		int index = addProductInfoData.getIndex();
		if (!productInfoMap.containsKey(productInfo.getId()) && index == storeProductInfo.getProductIdIndex() + 1) {
			productInfoMap.put(productInfo.getId(), productInfo);
			storeProductInfo.setProductIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeProductInfo(StoreProductInfo storeProductInfo, RemoveProductInfoData removeProductInfoData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
		if (productInfoMap.containsKey(removeProductInfoData.getId())) {
			ProductInfo info = productInfoMap.get(removeProductInfoData.getId());
			info.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}
	
	public boolean updateProductInfo(StoreProductInfo storeProductInfo, UpdateProductInfoData inputData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
		if (productInfoMap.containsKey(inputData.getId())) {
			ProductInfo oldData = productInfoMap.get(inputData.getId());
			ProductInfo newData = AppUtils.copyBeanBySerialize(oldData, ProductInfo.class);
			inputData.updateProductInfo(newData);
			if(!oldData.equals(newData)) {
				productInfoMap.put(newData.getId(), newData);
				success = true;
			}
		}
		return success;
	}

	public boolean updateProductState(StoreProductInfo storeProductInfo, UpdateProductStateData inputData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
		if (productInfoMap.containsKey(inputData.getId())) {
			ProductInfo productInfo = productInfoMap.get(inputData.getId());
			productInfo.setState(inputData.getState());
			productInfoMap.put(productInfo.getId(), productInfo);
			success = true;
		}
		return success;
	}

	/********************************** 项目分类Bean基本操作 **********************************/
	public boolean addProductType(StoreProductInfo storeProductInfo, AddProductTypeData inputData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		ProductType productType = inputData.toProductType();
		Map<String, ProductType> productTypeMap = storeProductInfo.getProductTypeMap();
		int index = inputData.getIndex();
		if (!productTypeMap.containsKey(productType.getId()) && index == storeProductInfo.getProductTypeIdIndex() + 1) {
			productTypeMap.put(productType.getId(), productType);
			storeProductInfo.setProductTypeIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeProductType(StoreProductInfo storeProductInfo, RemoveProductTypeData inputData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductType> productTypeMap = storeProductInfo.getProductTypeMap();
		if (productTypeMap.containsKey(inputData.getId())) {
			ProductType productType = productTypeMap.get(inputData.getId());
			productType.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}

	public boolean updateProductType(StoreProductInfo storeProductInfo, UpdateProductTypeData inputData) {
		if(storeProductInfo == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductType> productTypeMap = storeProductInfo.getProductTypeMap();
		if (productTypeMap.containsKey(inputData.getId())) {
			ProductType productType = productTypeMap.get(inputData.getId());
			inputData.updateProductType(productType);
			productType.setLastUpdateTime(System.currentTimeMillis());
			productTypeMap.put(inputData.getId(), productType);
			success = true;
		}
		return success;
	}
}
