package com.hq.chainStore.service.workFlowData.data;

/**
 * 购买项的类型
 */
public enum RecordTypeEnum {
	Buy("购买"), 
	Donation("赠送"),
	;

	private String mark;

	private RecordTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static RecordTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
