package com.hq.storeManagerRestClient.service.muser.apiData;

import com.hq.storeManagerRestClient.service.muser.data.MUser;

public class LoginResp {

	private MUser muser;

	private String token;

	public static LoginResp newInstance() {
		LoginResp resp = new LoginResp();
		return resp;
	}

	public MUser getMuser() {
		return muser;
	}

	public void setMuser(MUser muser) {
		this.muser = muser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
