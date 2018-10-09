package com.hq.thirdPartyClient.service.sms.apiData;

public class VerifyCodeForm {
	private String phoneNumbers;//目标号码 多个号码请用,分割 最多支持200个号码
	private int smsType;//短信类型 如：验证码、邀请通知、预约通知等 SmsTypeEnum
	private int originType;//来源 storeMs/customerMs等   OriginTypeEnum

	public static VerifyCodeForm newInstance() {
		return new VerifyCodeForm();
	}
	
	public static VerifyCodeForm newInstance(String phone, int smsType, int originType) {
		VerifyCodeForm data = newInstance();
		data.phoneNumbers=phone;
		data.smsType=smsType;
		data.originType=originType;
		return data;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public int getSmsType() {
		return smsType;
	}

	public void setSmsType(int smsType) {
		this.smsType = smsType;
	}

	public int getOriginType() {
		return originType;
	}

	public void setOriginType(int originType) {
		this.originType = originType;
	}
}
