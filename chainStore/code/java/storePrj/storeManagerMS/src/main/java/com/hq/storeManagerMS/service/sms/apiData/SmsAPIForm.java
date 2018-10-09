package com.hq.storeManagerMS.service.sms.apiData;

import java.util.HashMap;
import java.util.Map;

public class SmsAPIForm {
	private String phoneNumbers;// 目标号码 多个号码请用,分割 最多支持200个号码
	private String content;// 短信验证码、应用下载链接等
	private int smsType;// 短信类型 如：验证码、邀请通知、预约通知等 SmsTypeEnum
	private int originType;// 来源 storeMs/customerMs等 OriginTypeEnum
	private String signName;// 智美通
	
	private Map<String,String> dictMap = new HashMap<String,String>();;// 短信验证码、应用下载链接等   如：{"code":"2546"} {"name":"lisi","url":"kjkk"}

	public static SmsAPIForm newInstance() {
		return new SmsAPIForm();
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Map<String, String> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<String, String> dictMap) {
		this.dictMap = dictMap;
	}

}
