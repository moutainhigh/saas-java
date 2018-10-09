package com.hq.customerMS.service.sms.data;

public enum SmsUseEnum {
	HAS_USE("已使用"),
	NOT_USE("未使用"),
	;
	
	private String mark;
	
	private SmsUseEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static SmsUseEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
