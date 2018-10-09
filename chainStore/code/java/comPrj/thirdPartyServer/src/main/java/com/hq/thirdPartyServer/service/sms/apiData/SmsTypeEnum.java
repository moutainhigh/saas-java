package com.hq.thirdPartyServer.service.sms.apiData;

public enum SmsTypeEnum {
	B_VERIFICATION_CODE("B端智美通注册验证码"), 
	C_VERIFICATION_CODE("C端智美通注册验证码"), 
	INVITATION_CODE("邀请加入店铺"), 
	
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
