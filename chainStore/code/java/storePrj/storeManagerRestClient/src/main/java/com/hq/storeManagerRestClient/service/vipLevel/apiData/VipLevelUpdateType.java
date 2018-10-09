package com.hq.storeManagerRestClient.service.vipLevel.apiData;

public enum VipLevelUpdateType {
	UpdateVipLevel("修改会员等级"),
	UpdateVipLevelState("修改会员等级状态")
	;

	private String descript;
	
	private VipLevelUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static VipLevelUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
