package com.hq.storeMS.service.saas.apiData;

public enum OPAdminRoleUpdateType {

	updateInfo,
	;
	public static OPAdminRoleUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
