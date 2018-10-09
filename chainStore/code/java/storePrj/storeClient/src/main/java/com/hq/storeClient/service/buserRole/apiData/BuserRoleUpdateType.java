package com.hq.storeClient.service.buserRole.apiData;

public enum BuserRoleUpdateType {
	UpdateInfo("修改基本信息"),
	
	;
	
	private String mark;
	
	private BuserRoleUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static BuserRoleUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
