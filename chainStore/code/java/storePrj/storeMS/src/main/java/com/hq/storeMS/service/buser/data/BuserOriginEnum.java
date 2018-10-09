package com.hq.storeMS.service.buser.data;

public enum BuserOriginEnum {
	Normal("普通注册"),
	Chain("连锁店注册"),
	;
	
	private String descript;
	
	private BuserOriginEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static BuserOriginEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
