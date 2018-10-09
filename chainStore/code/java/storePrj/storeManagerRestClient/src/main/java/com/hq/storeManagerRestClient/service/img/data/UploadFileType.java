package com.hq.storeManagerRestClient.service.img.data;

public enum UploadFileType {

	IMG("img"),  //图片
	VIDEO("video"), //视频
	FILE("file"), //视频
	;
	
	private String type;
	
	private UploadFileType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static UploadFileType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
