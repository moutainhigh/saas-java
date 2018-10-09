package com.hq.storeMS.service.buser.apiData;

public enum BUserUpdateType {
	updateInfo("修改用户信息"),
	changePassword("修改密码"),
	UpdateVipType("修改会员类型"),
	VipRecharge("会员充值"),
	BindingWeChat("绑定微信UnionId"),
	UnBindingWeChat("解绑微信UnionId"),
	;
	
	private String descript;
	
	private BUserUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static BUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
