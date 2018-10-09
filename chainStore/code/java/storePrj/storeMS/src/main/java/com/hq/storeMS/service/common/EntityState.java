package com.hq.storeMS.service.common;

//实体类状态，软删除用
public enum EntityState {

	Normal,
	Deleted,
	;
	
	public static EntityState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
