package com.hq.storeMS.service.download.data;


public enum DownLoadTypeEnum{
	StoreLeaguer("店铺客户"),
	;
	
	private String mark;
	
	private DownLoadTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DownLoadTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
