package com.hq.storeMS.service.store.apiData;

public enum StoreFindTypeEnum {
	All,
	Ownered,
	Joined,
	;
	
	public static StoreFindTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
