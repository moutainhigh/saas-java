package com.hq.testClass.robot.storeMaterialInfo.robot;

import org.apache.commons.lang3.RandomUtils;

public class MaterialRobotData {

	private long storeId;
	
	private String name;
	
	//参考价格
	private float referencePrice;
	
	//库存
	private int inventory;
	
	//阈值、库存临界值
	private int threshold;
	
	private String id;
	
	private String imgUrl;

	public static MaterialRobotData newInstance(int mark){
		MaterialRobotData robotData = new MaterialRobotData();
		robotData.name = "耗材" + mark;
		robotData.referencePrice = mark;
		robotData.inventory = RandomUtils.nextInt(200, 400);
		robotData.threshold = RandomUtils.nextInt(20, 200);
		robotData.imgUrl = "img/store/15/joinStoreQrCode/2017_09_12_10_fc3c6c9f-3ed0-4c57-840e-ac461a11827a.jpg";
		return robotData;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
