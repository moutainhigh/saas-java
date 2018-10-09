package com.hq.storeMS.service.sellItem.data;

public enum SellItemTypeEnum {
	PRDCARD("次卡"), 
	PRODUCT("项目"), 
	GOODS("商品"), 
	PACKAGE("套餐"), 
	;

	private String mark;

	private SellItemTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SellItemTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
