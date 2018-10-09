package com.hq.chainStore.pressTest.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductType;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.testClass.robot.storeProductInfo.robot.StoreProductRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoActor {

	public static StoreProductInfoActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreProductInfoActor.class);
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @return
	 */
	public StoreProductInfo get(long storeId){
		return StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
	}
	
	/**
	 * 获取项目列表
	 * @param storeId
	 * @return
	 */
	public List<ProductInfo> getProductList(long storeId){
		return StoreProductInfoMgr.getInstance().getProductList(storeId);
	}
	
	/**
	 * 获取随机项目
	 * @param storeId
	 * @return
	 */
	public ProductInfo getRandomProduct(long storeId){
		List<ProductInfo> productList = StoreProductInfoMgr.getInstance().getProductList(storeId);
		if(productList.size() > 0){
			return productList.get(RandomUtils.nextInt(0, productList.size()));
		}else{
			return null;
		}
	}
	
	/**
	 * 添加项目
	 * @param storeId
	 */
	public void addProduct(long storeId){
		StoreProductInfo storeProductInfo = get(storeId);
		StoreProductRobotData robotData = StoreProductRobotData.newInstance(RandomUtils.nextInt(10, 100));
		AddProductInfoData addProductInfoData = AddProductInfoData.newInstance();
		FastBeanCopyer.getInstance().copy(robotData, addProductInfoData);
		addProductInfoData.setStoreId(storeId);
		addProductInfoData.setIndex(storeProductInfo.getProductIdIndex()+1);
		addProductInfoData.getImgPathList().add(robotData.getPath());
		ArrayList<ProductType> productTypeList = new ArrayList<ProductType>(storeProductInfo.getProductTypeMap().values());
		if(productTypeList.size() > 0){
			ProductType productType = productTypeList.get(RandomUtils.nextInt(0,productTypeList.size()));
			addProductInfoData.setTypeId(productType!=null?productType.getId():"0");
		}
		StoreProductInfoMgr.getInstance().addProductInfo(storeId, addProductInfoData);
	}
	
	/**
	 * 添加项目分类
	 * @param storeId
	 */
	public void addProductType(long storeId){
		StoreProductInfo storeProductInfo = get(storeId);
		AddProductTypeData addProductTypeData = AddProductTypeData.newInstance();
		addProductTypeData.setIndex(storeProductInfo.getProductTypeIdIndex()+1);
		addProductTypeData.setStoreId(storeId);
		addProductTypeData.setName("项目分类-"+addProductTypeData.getIndex());
		StoreProductInfoMgr.getInstance().addProductType(storeId, addProductTypeData);
	}
	
}
