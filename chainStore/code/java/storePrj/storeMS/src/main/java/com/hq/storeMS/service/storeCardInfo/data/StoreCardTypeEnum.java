package com.hq.storeMS.service.storeCardInfo.data;

public enum StoreCardTypeEnum {
	MEMBERSHIPCARD("会员卡"), 
	PRODUCTCARD("耗卡"), 
	DISCOUNTCARD("优惠卷"), 
	;

	private String mark;

	private StoreCardTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static StoreCardTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
