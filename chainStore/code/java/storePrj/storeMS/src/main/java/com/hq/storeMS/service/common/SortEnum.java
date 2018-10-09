package com.hq.storeMS.service.common;

public enum SortEnum {
	DESC("降序"), 
	ASE("升序"), 
	;

	private String mark;

	private SortEnum(String mark) {
		this.mark = mark;
	}

	public static SortEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
