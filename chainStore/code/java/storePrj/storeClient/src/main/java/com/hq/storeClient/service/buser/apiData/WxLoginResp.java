package com.hq.storeClient.service.buser.apiData;

import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class WxLoginResp {
	// 对应 LoginCodeEnum
	private int code;
	private String tips;

	private BUser buser;
	private String token;

	public static WxLoginResp newInstance() {
		WxLoginResp resp = new WxLoginResp();
		return resp;
	}

	public BUser getBuser() {
		return buser;
	}

	public void setBuser(BUser buser) {
		this.buser = (BUser) buser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}
