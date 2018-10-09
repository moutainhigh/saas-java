package com.hq.thirdPartyClient.service.sms.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SmsResult {

	public static SmsResult newInstance(boolean successP) {
		SmsResult data = new SmsResult();
		data.success = successP;
		return data;
	}

	private boolean success;
	private String verifyCode;// 验证码

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
