package com.hq.payms.service.alipayRecord.data;

public enum AlipayTypeEnum {
	SM("扫码支付"), 
	TM("条码支付"), 
	;

	private String mark;

	private AlipayTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AlipayTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
