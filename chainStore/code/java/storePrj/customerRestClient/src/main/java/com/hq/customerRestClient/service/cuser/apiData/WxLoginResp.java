package com.hq.customerRestClient.service.cuser.apiData;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class WxLoginResp {
	// 对应 LoginCodeEnum
	private int code;
	private String tips;

	private CUser cuser;
	private String token;

	public static WxLoginResp newInstance(LoginCodeEnum loginCodeEnum) {
		WxLoginResp resp = new WxLoginResp();
		resp.code = loginCodeEnum.ordinal();
		resp.tips = loginCodeEnum.getDescript();
		return resp;
	}

	public static WxLoginResp newInstance(CUser cuser, String token) {
		WxLoginResp resp = newInstance(LoginCodeEnum.Success);
		resp.cuser = (CUser) cuser;
		resp.token = token;
		return resp;
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

	public CUser getCuser() {
		return cuser;
	}

	public void setCuser(CUser cuser) {
		this.cuser = cuser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
