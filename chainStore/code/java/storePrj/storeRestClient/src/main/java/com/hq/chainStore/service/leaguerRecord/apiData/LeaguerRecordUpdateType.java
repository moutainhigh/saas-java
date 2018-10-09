package com.hq.chainStore.service.leaguerRecord.apiData;

public enum LeaguerRecordUpdateType {
	UpdateInfo("修改基本信息"),
	
	;
	
	private String mark;
	
	private LeaguerRecordUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static LeaguerRecordUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
