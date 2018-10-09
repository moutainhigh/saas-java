package com.hq.chainMS.service.chainUser.apiData;

import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LoginResp {
	private ChainUser chainUser;
	private String token;

	public static LoginResp newInstance(ChainUser chainUser, String token) {
		LoginResp resp = new LoginResp();
		resp.chainUser = (ChainUser) chainUser;
		resp.token = token;
		return resp;
	}

	public ChainUser getChainUser() {
		return chainUser;
	}

	public void setChainUser(ChainUser chainUser) {
		this.chainUser = (ChainUser) chainUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
