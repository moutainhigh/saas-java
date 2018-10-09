package com.hq.chainStore.service.orderComment.apiData;

public enum UpdateOrderCommentType {
	UpdateInfo("修改基本信息"),
	SaveBeauticianComment("保存医美师评论"),
	;
	
	private String mark;
	
	private UpdateOrderCommentType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static UpdateOrderCommentType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
