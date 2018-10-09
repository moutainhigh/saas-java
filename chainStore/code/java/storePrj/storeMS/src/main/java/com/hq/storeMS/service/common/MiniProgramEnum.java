package com.hq.storeMS.service.common;

public enum MiniProgramEnum {
	StoreMgr("智美通管理"), 
	Marketing("营销助手"),
	Customer("智美预约"),
	;

	private String mark;

	private MiniProgramEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MiniProgramEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
