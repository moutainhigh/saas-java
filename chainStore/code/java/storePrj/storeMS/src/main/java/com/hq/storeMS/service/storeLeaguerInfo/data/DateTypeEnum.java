package com.hq.storeMS.service.storeLeaguerInfo.data;


public enum DateTypeEnum {
	SOLARDATE("阳历"),
	LUNARDATE("阴历"), 
	;

	private String mark;

	private DateTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DateTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
