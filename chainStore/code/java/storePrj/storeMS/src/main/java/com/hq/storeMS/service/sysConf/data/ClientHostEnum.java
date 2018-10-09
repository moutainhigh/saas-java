package com.hq.storeMS.service.sysConf.data;

public enum ClientHostEnum {
	fileHost("fileHost", "http://{}/storefilems/ws/v1"),
	logoPath("logoPath", "http://{}/img/logo/store/zhimeitong-logo.png"),     
	orderHost("orderHost", "http://{}/orderms/ws/v1"),
	customerHost("customerHost", "http://{}/customerms/ws/v1"),
	thirdPartyHost("thirdPartyHost", "http://{}/thirdpartyserver/ws/v1"),
	chainHost("chainHost", "http://{}/chainms/ws/v1"),
	mngHost("mngHost", "http://{}/storemngms/ws/v1"),
	iotHost("iotHost", "http://{}/iotms/ws/v1"),
	payHost("payHost", "http://{}/payms/ws/v1"),
	;
	
	private String hostName;
	private String value;
	
	private ClientHostEnum(String hostNameP, String valueP) {
		this.hostName = hostNameP;
		this.value = valueP;
	}

	public static ClientHostEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

	public String getHostName() {
		return hostName;
	}

	public String getValue() {
		return value;
	}
}
