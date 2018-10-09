package com.hq.chainStore.service.orderComment.data;

public enum OrderCommentStatusEnum {
	NOT_COMMNET("未评论"),
	HAS_COMMNET("已评论"),
	;

	private String mark;

	private OrderCommentStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderCommentStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
