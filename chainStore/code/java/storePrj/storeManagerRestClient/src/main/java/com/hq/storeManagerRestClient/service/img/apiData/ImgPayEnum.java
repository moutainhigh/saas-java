package com.hq.storeManagerRestClient.service.img.apiData;

public enum ImgPayEnum {
	UNKNOW("未知"),
	ALIPAY("支付宝"),
	WECHAT("微信"),
	;
	
	private String mark;
	
	private ImgPayEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ImgPayEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
