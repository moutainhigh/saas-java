package com.hq.payms.service.pay.apiData;

public enum ApiTypeEnum {
	WXPAY("微信支付API"), 
	ALIPAY("支付宝API"), 
	;

	private String mark;

	private ApiTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ApiTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
