package com.hq.storeMS.service.arrearage.data;

public enum PaymentTypeEnum {
	Payment("还款"), 
	ChargeBack("退单"), 
	;
	
	private String mark;
	
	private PaymentTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static PaymentTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
