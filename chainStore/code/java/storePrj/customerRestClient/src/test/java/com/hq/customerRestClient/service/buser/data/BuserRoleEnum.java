package com.hq.customerRestClient.service.buser.data;

public enum BuserRoleEnum {
	BOSS("老板"),
	CLERK("员工"),
	INIT("未分配"),
	;
	
	private String descript;
	
	private BuserRoleEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static BuserRoleEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
