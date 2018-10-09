package com.hq.storeManagerRestClient.service.muser.data;

public enum MUserStatusEnum {
	Available,  //有效
	Invalide,	//失效
	Delete,		//删除
	;
	
	public static MUserStatusEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
