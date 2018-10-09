package com.hq.storeMS.service.dynamic.data;

public enum DynamicStatusEnum {
	New("新建"), 
	Release("发布"),
	;

	private String mark;

	private DynamicStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DynamicStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
