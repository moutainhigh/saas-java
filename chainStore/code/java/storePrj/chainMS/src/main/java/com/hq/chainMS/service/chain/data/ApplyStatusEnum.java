package com.hq.chainMS.service.chain.data;

public enum ApplyStatusEnum {
	PASS("审核通过"),
	UNPASS("审核不通过"),
	;
	
	private String mark;
	
	private ApplyStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ApplyStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
