package com.hq.storeClient.service.vipLevel.data;

public enum VipLevelStateEnum {

	OPEN("上架"),
	CLOSE("下架")
	;
	
	
	private String mark;
	
	private VipLevelStateEnum(String mark){
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static VipLevelStateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
