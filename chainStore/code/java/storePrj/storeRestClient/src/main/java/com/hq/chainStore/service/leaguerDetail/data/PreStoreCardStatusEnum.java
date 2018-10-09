package com.hq.chainStore.service.leaguerDetail.data;

public enum PreStoreCardStatusEnum {
	VALID("有效"),
	INVALID("失效"),
	;

	private String mark;

	private PreStoreCardStatusEnum(String mark) {
		this.mark = mark;
	}

	public static PreStoreCardStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
