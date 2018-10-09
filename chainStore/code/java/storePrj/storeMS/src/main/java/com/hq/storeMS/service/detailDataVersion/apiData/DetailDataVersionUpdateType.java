package com.hq.storeMS.service.detailDataVersion.apiData;

public enum DetailDataVersionUpdateType {

	AddBeauticianInfo("添加医美师"),    //添加医美师
	UpdateBeauticianInfo("修改医美师信息"), //修改医美师信息
	RemoveBeauticianInfo("删除医美师"), //删除医美师
	;
	
	private String descript;
	
	private DetailDataVersionUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static DetailDataVersionUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
