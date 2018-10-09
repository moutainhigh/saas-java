package com.hq.chainStore.service.opuser.data.adminRole;

public enum OPAdminRoleState {
	
	Available,  //有效
	Invalide,	//失效
	Delete,		//删除
	;
	
	public static OPAdminRoleState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
