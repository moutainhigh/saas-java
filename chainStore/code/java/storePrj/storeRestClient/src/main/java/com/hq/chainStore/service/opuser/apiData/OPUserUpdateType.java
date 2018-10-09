package com.hq.chainStore.service.opuser.apiData;

public enum OPUserUpdateType {

	updateInfo,
	updateRole,
	changePassword,
	;
	public static OPUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
