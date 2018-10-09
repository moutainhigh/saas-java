package com.hq.chainStore.service.report.apiData;

public enum UpdateSpecialDataType {
	AddBeauticianSpecialData("新增医美师特别关心数据"),
	AddProductSpecialData("新增项目特别关心数据"),
	AddCUserSpecialData("新增客户特别关心数据"),
	
	DelBeauticianSpecialData("删除医美师特别关心数据"),
	DelProductSpecialData("删除项目特别关心数据"),
	DelCUserSpecialData("删除客户特别关心数据"),
	;
	
	private String mark;
	
	private UpdateSpecialDataType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static UpdateSpecialDataType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
