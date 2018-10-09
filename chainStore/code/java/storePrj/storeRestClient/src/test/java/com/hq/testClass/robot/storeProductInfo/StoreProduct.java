package com.hq.testClass.robot.storeProductInfo;

import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.testClass.robot.storeProductInfo.robot.StoreProductRobot;

public class StoreProduct {

	private StoreProductRobot robot; 
	
	public static StoreProduct newInstance(StoreProductRobot robot){
		StoreProduct product = new StoreProduct();
		product.robot = robot;
		return product;
	}
	
	public StoreProductInfo addStoreProduct(long storeId,long id) {
		return robot.add(storeId,id);
	}
	
	public StoreProductInfo getById(){
		return robot.getById();
	}
	
	public void updateInfo(String productId){
		robot.updateInfo(productId);
	}
	
	public void updateState(String productId){
		robot.updateState(productId);
	}
	
	public void removeProduct(String productId){
		robot.removeProduct(productId);
	}

	
	public void addToTop(String productId){
		robot.addToTop(productId);
	}
	
	public void cancelFromTop(String productId){
		robot.cancelFromTop(productId);
	}
	
	public long getId(){
		return this.robot.getId();
	}
	
	public String getName(){
		return this.robot.getName();
	}
}
