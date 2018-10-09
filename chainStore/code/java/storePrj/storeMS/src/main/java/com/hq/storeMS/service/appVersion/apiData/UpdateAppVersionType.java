package com.hq.storeMS.service.appVersion.apiData;


public enum UpdateAppVersionType {
	UpdateAppVersionInfo("更新基本信息"), 
	UpdateAppVersionStatus("更新状态"),
	;
	
	private String descript;
	
	private UpdateAppVersionType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static UpdateAppVersionType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
