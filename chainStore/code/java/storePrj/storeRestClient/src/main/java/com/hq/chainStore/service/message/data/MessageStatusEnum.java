package com.hq.chainStore.service.message.data;

public enum MessageStatusEnum {
	Unread("未读"), 
	Read("已读"),
	;
	private String mark;

	private MessageStatusEnum(String mark) {
		this.mark = mark;
	}

	public static MessageStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
