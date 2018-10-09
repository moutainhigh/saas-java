package com.hq.chainStore.service.materialRecords.apiData;

public class MaterialRecordsAddApiData {

	private long storeId;

	private String materialId;

	private float price;

	private int count;
	//对应进货人
	private String userName;
	//对应进货图片url
	private String imgUrl;

	public static MaterialRecordsAddApiData newInstance() {
		return new MaterialRecordsAddApiData();
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
