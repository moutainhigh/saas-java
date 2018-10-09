package com.hq.customerMS.service.cuser.apiData;

public enum CUserUpdateType {
	UpdateInfo("修改用户信息"),
	ChangePassword("修改密码"),
	AddAddress("添加收货地址"),
	UpdateAddress("修改收货地址"),
	RemoveAddress("删除收货地址"),
	ChangeDefaultAddress("变更默认收货地址"),
	;
	
	private String descript;
	
	private CUserUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static CUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
