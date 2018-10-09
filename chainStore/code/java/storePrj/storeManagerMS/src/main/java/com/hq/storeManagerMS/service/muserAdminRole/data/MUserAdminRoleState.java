package com.hq.storeManagerMS.service.muserAdminRole.data;

public enum MUserAdminRoleState {
	Available,  //有效
	Invalide,	//失效
	Delete,		//删除
	;
	
	public static MUserAdminRoleState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
