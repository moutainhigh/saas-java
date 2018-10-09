package com.hq.storeMS.service.saas.apiData;

public enum OPStoreUpdateType {

	updateState,
	;
	public static OPStoreUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
