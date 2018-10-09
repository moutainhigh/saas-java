package com.hq.storeManagerMS.service.vipLevelType.apiData;

public enum VipLevelTypeUpdateType {
	UpdateVipLevelType("修改会员等级分类"),
	UpdateVipLevelTypeState("修改会员等级分类状态"),
	RemoveVipLevelType("删除等级分类")
	;

	private String descript;
	
	private VipLevelTypeUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static VipLevelTypeUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
