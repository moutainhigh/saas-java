package com.hq.thirdPartyClient.service.sms.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.hq.thirdPartyClient.service.common.ThirdPartyClientConstants;
import com.hq.thirdPartyClient.service.sms.apiData.VerifyCodeForm;
import com.zenmind.common.json.JsonUtil;

public class SmsServerForm {
	private String phoneNumbers;// 目标号码 多个号码请用,分割 最多支持200个号码
	private int smsType;// 短信类型 如：验证码、邀请通知、预约通知等 SmsTypeEnum
	private int originType;// 来源 storeMs/customerMs等 OriginTypeEnum

	private String content;// 短信验证码、应用下载链接等 Json格式的字符串， 对应短信模板里面的变量名称 如验证码{"code":"122"}
	private String signName;// 智美通

	// 模板参数
	private String time;
	private String verifyCode;

	public static SmsServerForm newInstance() {
		return new SmsServerForm();
	}

	public static SmsServerForm newInstanceByVerifyCodeForm(VerifyCodeForm codeForm) {
		SmsServerForm data = newInstance();
		data.phoneNumbers = codeForm.getPhoneNumbers();
		data.smsType = codeForm.getSmsType();
		data.originType = codeForm.getOriginType();

		data.time = String.valueOf(ThirdPartyClientConstants.EFFICTIVE_TIME);
		data.verifyCode = RandomStringUtils.random(4, ThirdPartyClientConstants.NUMBER_RANDOM);

		Map<String, String> dictMap = new HashMap<String, String>();
		dictMap.put("time", data.time);
		dictMap.put("code", data.verifyCode);
		data.content = JsonUtil.getInstance().toJson(dictMap);
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
