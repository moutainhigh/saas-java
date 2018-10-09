package com.hq.storeMS.service.dynamic.apiData;


public enum DynamicUpdateType {
	UpdateDynamicInfo("更新基本信息"), 
	UpdateDynamicStatus("更新状态"),
	;
	
	private String descript;
	
	private DynamicUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static DynamicUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
