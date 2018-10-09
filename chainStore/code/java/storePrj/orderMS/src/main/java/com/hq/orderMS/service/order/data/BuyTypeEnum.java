package com.hq.orderMS.service.order.data;

/**
 * 购买类型
 */
public enum BuyTypeEnum {
	PRDCARD("次卡"), 
	PRODUCT("项目"), 
	GOODS("商品"), 
	RECHARGE("充值"), 
	PACKAGE("套餐"), 
	;

	private String mark;

	private BuyTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static BuyTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
