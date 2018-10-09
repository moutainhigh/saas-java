package com.hq.storeMS.service.workFlowData.apiData;


public enum ProdRecordUpdateEnum {
	updateProductInfo("更新项目基本信息"), 
	updateProductStatus("更新项目状态"),
	;
	
	private String descript;
	
	private ProdRecordUpdateEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static ProdRecordUpdateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
