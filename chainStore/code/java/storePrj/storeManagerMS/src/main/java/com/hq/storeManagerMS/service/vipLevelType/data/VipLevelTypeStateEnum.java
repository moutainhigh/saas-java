package com.hq.storeManagerMS.service.vipLevelType.data;

public enum VipLevelTypeStateEnum {

	OPEN("上架"),
	CLOSE("下架")
	;
	
	
	private String mark;
	
	private VipLevelTypeStateEnum(String mark){
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static VipLevelTypeStateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
