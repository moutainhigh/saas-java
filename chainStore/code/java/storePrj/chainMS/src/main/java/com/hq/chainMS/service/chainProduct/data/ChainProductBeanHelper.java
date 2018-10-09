package com.hq.chainMS.service.chainProduct.data;

import java.util.Map;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainProduct.apiData.AddProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.AddProductTypeData;
import com.hq.chainMS.service.chainProduct.apiData.ProductAllotForm;
import com.hq.chainMS.service.chainProduct.apiData.RemoveProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.RemoveProductTypeData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductTypeData;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductBeanHelper {

	public static ChainProductBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductBeanHelper.class);
	}

	/********************************** 项目Bean基本操作 **********************************/
	public boolean addProductInfo(ChainProduct chainProduct, AddProductInfoData addProductInfoData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Product productInfo = addProductInfoData.toProductInfo(chainProduct.getChainId());
		Map<String, Product> productInfoMap = chainProduct.getProductInfoMap();
		long index = addProductInfoData.getIndex();
		if (!productInfoMap.containsKey(productInfo.getId()) && index == chainProduct.getProductIdIndex() + 1) {
			productInfoMap.put(productInfo.getId(), productInfo);
			chainProduct.setProductIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeProductInfo(ChainProduct chainProduct, RemoveProductInfoData removeProductInfoData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, Product> productInfoMap = chainProduct.getProductInfoMap();
		if (productInfoMap.containsKey(removeProductInfoData.getId())) {
			Product info = productInfoMap.get(removeProductInfoData.getId());
			info.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}
	
	public boolean updateProductInfo(ChainProduct chainProduct, UpdateProductInfoData inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, Product> productInfoMap = chainProduct.getProductInfoMap();
		if (productInfoMap.containsKey(inputData.getId())) {
			Product oldTarget = productInfoMap.get(inputData.getId());
			Product newTarget = AppUtils.copyBeanBySerialize(oldTarget, Product.class);
			inputData.updateProductInfo(newTarget);
			if(!newTarget.equals(oldTarget)) {
				productInfoMap.put(newTarget.getId(), newTarget);
				success = true;
			}
		}
		return success;
	}

	public boolean updateProductState(ChainProduct chainProduct, UpdateProductStateData inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, Product> productInfoMap = chainProduct.getProductInfoMap();
		if (productInfoMap.containsKey(inputData.getId())) {
			Product productInfo = productInfoMap.get(inputData.getId());
			if(inputData.getState() != productInfo.getState()) {
				productInfo.setState(inputData.getState());
				productInfoMap.put(productInfo.getId(), productInfo);
				success = true;
			}
		}
		return success;
	}
	
	public boolean allotStore(ChainProduct chainProduct, ProductAllotForm inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, Product> productInfoMap = chainProduct.getProductInfoMap();
		if (productInfoMap.containsKey(inputData.getId())) {
			Product productInfo = productInfoMap.get(inputData.getId());
			productInfo.setApplyStoreIds(inputData.getApplyStoreIds());
			success = true;
		}
		return success;
	}

	/********************************** 项目分类Bean基本操作 **********************************/
	public boolean addProductType(ChainProduct chainProduct, AddProductTypeData inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		ProductType productType = inputData.toProductType(chainProduct.getChainId());
		Map<String, ProductType> productTypeMap = chainProduct.getProductTypeMap();
		long index = inputData.getIndex();
		if (!productTypeMap.containsKey(productType.getId()) && index == chainProduct.getProductTypeIdIndex() + 1) {
			productTypeMap.put(productType.getId(), productType);
			chainProduct.setProductTypeIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeProductType(ChainProduct chainProduct, RemoveProductTypeData inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductType> productTypeMap = chainProduct.getProductTypeMap();
		if (productTypeMap.containsKey(inputData.getId())) {
			ProductType productType = productTypeMap.get(inputData.getId());
			productType.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}

	public boolean updateProductType(ChainProduct chainProduct, UpdateProductTypeData inputData) {
		if(chainProduct == null){
			return false;
		}
		boolean success = false;
		Map<String, ProductType> productTypeMap = chainProduct.getProductTypeMap();
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
