package com.hq.customerRestClient.service.cuser.apiData;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LoginResp {
	private CUser cuser;

	private String token;

	public static LoginResp newInstance(CUser cuser, String token) {
		LoginResp resp = new LoginResp();
		resp.cuser = (CUser) cuser;
		resp.token = token;
		return resp;
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
