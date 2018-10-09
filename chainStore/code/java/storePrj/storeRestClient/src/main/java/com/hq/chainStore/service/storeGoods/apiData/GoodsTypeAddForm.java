package com.hq.chainStore.service.storeGoods.apiData;


public class GoodsTypeAddForm {
	private long index;
	private String name;

	public static GoodsTypeAddForm newInstance() {
		return new GoodsTypeAddForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

}
