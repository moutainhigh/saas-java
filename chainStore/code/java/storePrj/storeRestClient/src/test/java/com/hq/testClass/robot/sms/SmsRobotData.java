package com.hq.testClass.robot.sms;

import org.apache.commons.lang3.RandomUtils;

public class SmsRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private String phoneNumbers;
	private String content;
	private int smsType;

	public static SmsRobotData newInstance(int mark) {
		SmsRobotData data = new SmsRobotData();
		data.mark = mark;
		data.smsType = RandomUtils.nextInt(0, 2);
		data.content = "content-" + mark;
		data.phoneNumbers = (13800000000L + mark) + "";
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
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

	@Override
	public String toString() {
		return "SmsRobotData [mark=" + mark + ", phoneNumbers=" + phoneNumbers
				+ ", content=" + content + ", smsType=" + smsType + "]";
	}

}
