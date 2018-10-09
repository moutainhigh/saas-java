package com.hq.storeMS.service.common;

public enum PromotionFlagEnum {
	No("非促销"),
	Yes("促销"),
	;
	
	private String mark;
	
	private PromotionFlagEnum(String markP) {
		this.mark = markP;
	}

	public static PromotionFlagEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

	public String getMark() {
		return mark;
	}
}
