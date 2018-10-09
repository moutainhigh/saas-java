package com.hq.chainStore.service.serverConfig.apiData;

public enum CfgType {

	IMGHOST,  //图片host
	;
	
	public static CfgType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
