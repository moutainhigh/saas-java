package com.hq.copyData.copyModule.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.chainStore.service.productDetail.bs.ProductDetailMgr;
import com.hq.chainStore.service.productDetail.data.ProductDetail;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductType;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyStoreProductInfo extends AbstractCopyModule {

	public static CopyStoreProductInfo newInstance() {
		CopyStoreProductInfo data = new CopyStoreProductInfo();
		return data;
	}
	
	public void copy() {
		//数据准备
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		List<ProductType> types = getProductTypes();
		List<ProductDetail> products = getProductDetails();
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		//数据拷贝
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(getTargetStoreId());
		addProductTypes(storeProductInfo, types);
		addProductDetails(storeProductInfo, products);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		System.out.println("copy store product info finish");
	}
	
	private List<ProductType> getProductTypes(){
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(getSourceStoreId());
		storeProductInfo.getProductTypeMap().remove("0");
		List<ProductType> types = new ArrayList<ProductType>(storeProductInfo.getProductTypeMap().values());
		Collections.sort(types, new Comparator<ProductType>() {
			@Override
			public int compare(ProductType o1, ProductType o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return types;
	}
	
	private List<ProductDetail> getProductDetails(){
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		return ProductDetailMgr.getInstance().getProductDetailPageInfo(queryForm).getList();
	}
	
	private void addProductTypes(StoreProductInfo storeProductInfo, List<ProductType> types){
		int productTypeIdIndex = storeProductInfo.getProductTypeIdIndex();
		for (ProductType productType : types) {
			productTypeIdIndex++;
			AddProductTypeData addProductTypeData = AddProductTypeData.newInstance();
			addProductTypeData.setIndex(productTypeIdIndex);
			addProductTypeData.setName(productType.getName());
			addProductTypeData.setStoreId(getTargetStoreId());
			StoreProductInfoMgr.getInstance().addProductType(getTargetStoreId(), addProductTypeData);
			
			if(productType.getEntityState() == EntityState.Deleted.ordinal()) {
				RemoveProductTypeData removeProductTypeData = RemoveProductTypeData.newInstance();
				removeProductTypeData.setId(productType.getId());
				removeProductTypeData.setStoreId(getTargetStoreId());
				StoreProductInfoMgr.getInstance().removeProductType(getTargetStoreId(), removeProductTypeData);
			}
		}
	}
	
	private void addProductDetails(StoreProductInfo storeProductInfo, List<ProductDetail> products){
		int productIdIndex = storeProductInfo.getProductIdIndex();
		for (ProductDetail productInfo : products) {
			if(productInfo.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			productIdIndex++;
			AddProductInfoData addProductInfoData = AddProductInfoData.newInstance();
			BeanUtils.copyProperties(productInfo, addProductInfoData, "id");
			addProductInfoData.setIndex(productIdIndex);
			addProductInfoData.setStoreId(getTargetStoreId());
			StoreProductInfoMgr.getInstance().addProductInfo(getTargetStoreId(), addProductInfoData);
		}
	}
}
