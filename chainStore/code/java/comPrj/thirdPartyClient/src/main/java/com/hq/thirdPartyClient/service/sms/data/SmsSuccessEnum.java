package com.hq.thirdPartyClient.service.sms.data;

public enum SmsSuccessEnum {
	//短信服务返回0 成功
	RESPONSE_SUCCESS("0"),
	//返回给客户端 成功码
	RETURN_SUCCESS("200"),
	
	;

	private String code;

	private SmsSuccessEnum(String code) {
		this.code = code;
	}

	public static SmsSuccessEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getCode() {
		return code;
	}
}
