package com.hq.chainStore.service.workFlowData.apiData;


public enum WorkFlowDataUpdateEnum {
	updateWorkFlowDataInfo("更新基本信息"), 
	updateWorkFlowDataStatus("更新状态"),
	updateWorkFlowDataBuyItems("更新购买项列表信息"),
	workFlowDataDeleteForm("删除工作流"),
	cancelWorkFlowData("工作流作废"),
	;
	
	private String descript;
	
	private WorkFlowDataUpdateEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static WorkFlowDataUpdateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
