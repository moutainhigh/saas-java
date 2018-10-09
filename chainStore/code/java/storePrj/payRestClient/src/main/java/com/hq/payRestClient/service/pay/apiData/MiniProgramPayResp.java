package com.hq.payRestClient.service.pay.apiData;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MiniProgramPayResp {
	private String timeStamp;
	private String nonceStr;
	private String _package; //小程序页面中要转成package参数
	private String signType; //签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
	private String paySign; //签名
	
	public static MiniProgramPayResp newInstance() {
		return new MiniProgramPayResp();
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String get_package() {
		return _package;
	}

	public void set_package(String _package) {
		this._package = _package;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
}
