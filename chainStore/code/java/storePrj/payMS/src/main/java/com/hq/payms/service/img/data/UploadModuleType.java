package com.hq.payms.service.img.data;

public enum UploadModuleType {

	Temp("temp"),
	Buser("buser"),
	Store("store"),
	Product("product"),
	QrCode("qrcode"),
	Cuser("cuser"),
	Leaguer("Leaguer"),
	Goods("Goods"),
	MemberShipCard("MemberShipCard"),
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
