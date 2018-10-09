package com.hq.storeMS.service.storeCardInfo.data;

public enum PrdInCardEnum {
	FOREVER("永久"), 
	LIMITTIME("限次数"), 
	;

	private String mark;

	private PrdInCardEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static PrdInCardEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
