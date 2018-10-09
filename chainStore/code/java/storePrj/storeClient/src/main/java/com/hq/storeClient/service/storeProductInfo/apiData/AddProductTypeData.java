package com.hq.storeClient.service.storeProductInfo.apiData;

public class AddProductTypeData {
	private int index;
	private long storeId;
	private String name;

	public static AddProductTypeData newInstance() {
		return new AddProductTypeData();
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
