package com.hq.chainClient.service.chainUser.apiData;

import com.hq.chainClient.service.chainUser.data.ChainUser;

public class LoginResp {
	private ChainUser chainUser;
	private String token;

	public static LoginResp newInstance() {
		LoginResp data = new LoginResp();
		return data;
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
