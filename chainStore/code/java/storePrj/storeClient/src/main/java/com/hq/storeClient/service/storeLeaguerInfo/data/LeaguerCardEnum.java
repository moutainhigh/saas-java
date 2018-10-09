package com.hq.storeClient.service.storeLeaguerInfo.data;

public enum LeaguerCardEnum {
	VALID("有效"),
	INVALID("失效"),
	NOTUSE("未使用"),
	USING("使用中"),
	FINISH("已完成"),
	BACKCARD("已退卡"),
	;

	private String mark;

	private LeaguerCardEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static LeaguerCardEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
