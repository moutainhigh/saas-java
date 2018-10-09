package com.hq.storeMS.service.workFlowType.apiData;


public enum UpdateWorkFlowTypeEnum {
	UpdateWorkFlowTypeInfo("更新基本信息"), 
	;
	
	private String descript;
	
	private UpdateWorkFlowTypeEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static UpdateWorkFlowTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
