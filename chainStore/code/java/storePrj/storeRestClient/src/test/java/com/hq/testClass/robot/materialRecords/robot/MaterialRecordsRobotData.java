package com.hq.testClass.robot.materialRecords.robot;

public class MaterialRecordsRobotData {

	private long storeId;
	
	private String materialId;

	private float price;
	
	private int count;
	
	private long id;
	
	private String userName;
	
	private String imgUrl;

	public static MaterialRecordsRobotData newInstance(int mark){
		MaterialRecordsRobotData robotData = new MaterialRecordsRobotData();
		robotData.count = 5000;
		robotData.price = 2000;
		robotData.userName = "小明";
		robotData.imgUrl = "img/store/12/2017_07_14_11_24424880-8832-4bd1-9190-281fde9c68e0.jpg";
		return robotData;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
