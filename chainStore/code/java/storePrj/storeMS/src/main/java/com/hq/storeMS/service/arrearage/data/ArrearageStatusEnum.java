package com.hq.storeMS.service.arrearage.data;

public enum ArrearageStatusEnum {
	BALANCE_DUE("欠账"), 
	FINISH("清账"), 
	;
	
	private String mark;
	
	private ArrearageStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ArrearageStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
