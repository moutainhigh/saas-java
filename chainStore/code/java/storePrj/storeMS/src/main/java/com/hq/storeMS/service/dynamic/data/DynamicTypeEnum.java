package com.hq.storeMS.service.dynamic.data;

public enum DynamicTypeEnum {
	NORMAL("普通动态"), 
	PRODUCT("产品动态"),
	;
	
	private String mark;

	private DynamicTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static DynamicTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
