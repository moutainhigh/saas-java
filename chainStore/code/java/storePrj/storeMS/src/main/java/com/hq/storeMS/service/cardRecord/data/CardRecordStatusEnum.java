package com.hq.storeMS.service.cardRecord.data;


public enum CardRecordStatusEnum{
	UNKNOW("未知"),
	VALID("有效"),
	UNVALID("无效"),
	;
	
	private String mark;
	
	private CardRecordStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static CardRecordStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
