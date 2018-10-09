package com.hq.chainMS.service.chainClerk.data.adminRole;

public enum AdminRoleState {
	Available,  //有效
	Invalide,	//失效
	Delete,		//删除
	;
	
	public static AdminRoleState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
