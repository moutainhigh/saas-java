package com.hq.storeClient.service.productCardDetail.data;

public enum ProductCardItemEnum {
	PRODUCT("项目"), 
	GOODS("商品"), 
	PACKAGE("套餐"), 
	;

	private String mark;

	private ProductCardItemEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ProductCardItemEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
