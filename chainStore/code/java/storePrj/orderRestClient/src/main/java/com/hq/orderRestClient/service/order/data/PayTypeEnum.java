package com.hq.orderRestClient.service.order.data;

public enum PayTypeEnum {
	CASH("现金"),
	ALIPAY("支付宝"),
	WECHAT("微信"),
	SLOT_CARD("刷卡"),
	MEMBERSHIPCARD("会员卡"),
	ARREARAGE("欠款"),
	;
	
	private String mark;
	
	private PayTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static PayTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
