package com.hq.storeClient.service.workFlowData.data;

public enum WfDataTypeEnum {
	NORMAL_RCD("普通开单"),
	OLD_RCD("补录开单"),
	;
	
	
	private String mark;
	
	private WfDataTypeEnum(String mark){
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static WfDataTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
