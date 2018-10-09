package com.hq.chainMS.service.chainUser.data;

public enum ChainUserRoleEnum {
	BOSS("老板"),
	CLERK("员工"),
	INIT("未分配"),
	;
	
	private String descript;
	
	private ChainUserRoleEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static ChainUserRoleEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
