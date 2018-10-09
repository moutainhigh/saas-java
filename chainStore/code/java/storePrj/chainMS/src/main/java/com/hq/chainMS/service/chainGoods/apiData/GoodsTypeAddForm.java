package com.hq.chainMS.service.chainGoods.apiData;

import com.hq.chainMS.service.chainGoods.data.GoodsType;

public class GoodsTypeAddForm {
	private long index;
	private String name;

	public static GoodsTypeAddForm newInstance() {
		return new GoodsTypeAddForm();
	}
	
	public GoodsType toGoodsType(long chainId) {
		GoodsType data = GoodsType.newInstance();
		data.setName(name);
		data.setId(GoodsType.generateId(chainId, index));
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
