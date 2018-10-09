package com.hq.storeManagerRestClient.service.sms.apiData;


public enum SmsTypeEnum {
	REG_CODE("注册验证码"), 
	;

	private String mark;

	private SmsTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SmsTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
