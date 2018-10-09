package com.hq.storeMS.service.beauticianProduct.apiData;

public enum BeauticianProductUpdateType {
	AddForemost("置顶项目"),
	RemoveForemost("取消置顶"),
	;
	
	private String mark;
	
	private BeauticianProductUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static BeauticianProductUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
