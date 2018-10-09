package com.hq.storeClient.service.buserRole.apiData;

public enum OperateEnum {
	Add("添加"),
	Remove("移除"),
	;
	
	private String mark;
	
	private OperateEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static OperateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
