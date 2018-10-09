package com.hq.chainStore.service.bonus.data;


public enum BonusRecordStatusEnum{
	VALID("有效"),
	UNVALID("无效"),
	;
	
	private String mark;
	
	private BonusRecordStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static BonusRecordStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
