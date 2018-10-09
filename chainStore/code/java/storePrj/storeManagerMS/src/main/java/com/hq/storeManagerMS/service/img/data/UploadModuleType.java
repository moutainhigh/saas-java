package com.hq.storeManagerMS.service.img.data;

public enum UploadModuleType {
	Temp("temp"),
	;
	
	private String type;
	
	private UploadModuleType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static UploadModuleType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
