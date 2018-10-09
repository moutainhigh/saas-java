package com.hq.chainClient.service.chainProduct.apiData;

public class AddProductTypeData {
	private long index;
	private String name;

	public static AddProductTypeData newInstance() {
		return new AddProductTypeData();
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
