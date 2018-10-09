package com.hq.storeClient.service.vipLevel.data;

public enum VipLevelTypeEnum {
	HonKonEdition("宏强定制版"),
	StandardEdition("标准版"),
	CustomisedEdition("大客户定制版")
	;
	
	
	private String mark;
	
	private VipLevelTypeEnum(String mark){
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static VipLevelTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
