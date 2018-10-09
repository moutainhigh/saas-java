package com.hq.storeManagerRestClient.service.sms.apiData;

import java.io.Serializable;

public class SmsResp implements Serializable {

	private static final long serialVersionUID = 1L;

	public static SmsResp newInstance() {
		return new SmsResp();
	}

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SmsResp [code=" + code + ", message=" + message + "]";
	}

}
