package com.hq.chainClient.service.chainUser.apiData;

public enum ChainUserUpdateType {
	UpdateInfo("修改用户信息"),
	ChangePassword("修改密码"),
	;
	
	private String descript;
	
	private ChainUserUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static ChainUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
