package com.hq.chainMS.service.sms.apiData;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SmsResp {
	private String code;
	private String message;

	public static SmsResp newInstance() {
		return new SmsResp();
	}

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
}
