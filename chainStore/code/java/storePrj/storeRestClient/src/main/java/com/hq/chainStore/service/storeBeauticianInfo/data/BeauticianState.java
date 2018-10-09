package com.hq.chainStore.service.storeBeauticianInfo.data;

public enum BeauticianState {

	ONWork("工作状态"),	 //工作状态
	OffWork("停工状态"),  //停工状态
	;
	
	private String descript;

	private BeauticianState(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static BeauticianState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
