package com.hq.storeMS.service.storeGoods.apiData;

import com.hq.storeMS.service.storeGoods.data.GoodsType;

public class GoodsTypeAddForm {
	private long index;
	private String name;

	public static GoodsTypeAddForm newInstance() {
		return new GoodsTypeAddForm();
	}
	
	public GoodsType toGoodsType() {
		GoodsType data = GoodsType.newInstance();
		data.setName(name);
		data.setId(String.valueOf(index));
		return data;
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
