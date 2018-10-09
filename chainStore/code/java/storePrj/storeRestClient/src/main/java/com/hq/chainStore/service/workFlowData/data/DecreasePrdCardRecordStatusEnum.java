package com.hq.chainStore.service.workFlowData.data;


public enum DecreasePrdCardRecordStatusEnum {
	NEWCARD("新购"),
	OWNERCARD("已购"),
	;

	private String mark;

	private DecreasePrdCardRecordStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DecreasePrdCardRecordStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
