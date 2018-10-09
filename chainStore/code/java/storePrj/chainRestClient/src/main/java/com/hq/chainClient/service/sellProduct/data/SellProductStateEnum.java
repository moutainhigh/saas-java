package com.hq.chainClient.service.sellProduct.data;

public enum SellProductStateEnum {
	UP("上架"), 
	DOWN("下架"), 
	;

	private String mark;

	private SellProductStateEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SellProductStateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
