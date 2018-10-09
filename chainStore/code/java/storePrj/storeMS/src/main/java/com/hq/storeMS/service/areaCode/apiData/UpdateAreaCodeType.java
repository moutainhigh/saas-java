package com.hq.storeMS.service.areaCode.apiData;


public enum UpdateAreaCodeType {
	UpdateAreaCodeInfo("更新基本信息"), 
	UpdateAreaCodeStatus("更新状态"),
	;
	
	private String descript;
	
	private UpdateAreaCodeType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static UpdateAreaCodeType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
