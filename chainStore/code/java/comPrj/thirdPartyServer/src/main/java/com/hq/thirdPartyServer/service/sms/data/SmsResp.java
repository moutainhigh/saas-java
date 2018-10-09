package com.hq.thirdPartyServer.service.sms.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SmsResp {
	private String serialNumber; // 请求的流水号
	private int code;
	private String message;

	public static SmsResp newInstance() {
		return new SmsResp();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
