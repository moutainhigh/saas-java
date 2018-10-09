package com.hq.chainMS.service.chainUser.data;

public enum CrossClerkEnum {
	True("跨店员工"),
	False("普通员工"),
	;
	
	private String descript;
	
	private CrossClerkEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static CrossClerkEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
