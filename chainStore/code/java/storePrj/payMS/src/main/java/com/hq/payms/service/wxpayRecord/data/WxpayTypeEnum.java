package com.hq.payms.service.wxpayRecord.data;

public enum WxpayTypeEnum {
	SM("扫码支付"), 
	SK("刷卡支付"), 
	MINIPROGRAM("小程序支付"), 
	;

	private String mark;

	private WxpayTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static WxpayTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
