package com.hq.chainStore.service.appointment.data;

@Deprecated
public enum LeaguerTypeEnum {
	NORMAL("普通消费者"), // 普通消费者
	LEAGUER("会员消费者"), // 会员
	;
	
	private String mark;
	
	private LeaguerTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static LeaguerTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
