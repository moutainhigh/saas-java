package com.hq.testClass.robot.storeProductInfo.robot;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.CancelProductFromTopData;
import com.hq.chainStore.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.chainStore.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.chainStore.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfoState;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;

public class StoreProductRobot {
	
	private StoreProductRobotData data;
	
	public static StoreProductRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static StoreProductRobot newInstance(int mark){
		StoreProductRobot robot = new StoreProductRobot();
		robot.data = StoreProductRobotData.newInstance(mark);
		return robot;
	}
	
	public StoreProductInfo add(long storeId,long id){
		AddProductInfoData addProductInfoData = AddProductInfoData.newInstance();
		addProductInfoData.setStoreId(storeId);
		addProductInfoData.setName(data.getName());
		addProductInfoData.setPrice(data.getPrice());
		addProductInfoData.setDescript(data.getDescript());
		addProductInfoData.setState(data.getState());
		addProductInfoData.setTypeId(data.getTypeId());
		addProductInfoData.setIndex((int)id);
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setAddProductInfoData(addProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductInfo);
		StoreProductInfoMgr.getInstance().update(storeId, updateForm);
		
		data.setId(storeId);
		data.setStoreId(storeId);
		
		StoreProductInfo StoreProductInfo = getById();
		return StoreProductInfo;
	}
	
	
	public StoreProductInfo getById(){
		return StoreProductInfoMgr.getInstance().findSimpleStoreInfo(data.getId());
	}
	
	public void updateInfo(String productId){
		UpdateProductInfoData updateProductInfoData = UpdateProductInfoData.newInstance();
		updateProductInfoData.setId(productId);
		updateProductInfoData.setName(data.getName());
		updateProductInfoData.setPrice(data.getPrice());
		updateProductInfoData.setTypeId(data.getTypeId());
		updateProductInfoData.setDescript("storeProduct_update");
		updateProductInfoData.setStoreId(data.getStoreId());
		updateProductInfoData.addImgPath(data.getPath());
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductInfoData(updateProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductInfo);
		StoreProductInfoMgr.getInstance().update(data.getId(), updateForm);
	}
	
	
	public void addToTop(String productId){
		AddProductToTopData addProductToTopData = AddProductToTopData.newInstance();
		addProductToTopData.setStoreId(data.getStoreId());
		addProductToTopData.setProductId(productId);
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setAddProductToTopData(addProductToTopData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.AddProductToTop);
		StoreProductInfoMgr.getInstance().update(data.getId(), updateForm);
	}
	
	public void cancelFromTop(String productId){
		CancelProductFromTopData cancelProductFromTopData = CancelProductFromTopData.newInstance();
		cancelProductFromTopData.setStoreId(data.getStoreId());
		cancelProductFromTopData.setProductId(productId);
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setCancelProductFromTopData(cancelProductFromTopData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.CancelProductFromTop);
		StoreProductInfoMgr.getInstance().update(data.getId(), updateForm);
	}
	
	public void updateState(String productId){
		UpdateProductStateData updateProductStateData = UpdateProductStateData.newInstance();
		updateProductStateData.setId(productId);
		updateProductStateData.setStoreId(data.getStoreId());
		updateProductStateData.setState(ProductInfoState.Open.ordinal());
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setUpdateProductStateData(updateProductStateData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.UpdateProductState);
		StoreProductInfoMgr.getInstance().update(data.getId(), updateForm);
	}
	
	public void removeProduct(String productId){
		RemoveProductInfoData removeProductInfoData = RemoveProductInfoData.newInstance();
		removeProductInfoData.setId(productId);
		removeProductInfoData.setStoreId(data.getStoreId());
		
		StoreProductInfoUpdateForm updateForm = StoreProductInfoUpdateForm.newInstance();
		updateForm.setRemoveProductInfoData(removeProductInfoData);
		updateForm.setUpdateTypeEnum(StoreProductInfoUpdateType.RemoveProductInfo);
		StoreProductInfoMgr.getInstance().update(data.getId(), updateForm);
	}
	
	public StoreProductRobotData getData() {
		return data;
	}

	public void setData(StoreProductRobotData data) {
		this.data = data;
	}

	public long getId(){
		return this.data.getId();
	}
	
	public String getName(){
		return this.data.getName();
	}
	
}
