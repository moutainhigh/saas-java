package com.hq.storeMS.service.chainDataSyn.data;


public enum ChainDataStatusEnum{
	HAVE("已获取"),
	NOT_HAVE("未获取"),
	;
	
	private String mark;
	
	private ChainDataStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ChainDataStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
