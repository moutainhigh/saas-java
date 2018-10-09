package com.hq.storeClient.service.buser.apiData;

import com.hq.storeClient.service.buser.data.BUser;


public class LoginResp {

	private BUser buser;
	
	private String token;
	
	public static LoginResp newInstance(BUser buser,String token){
		LoginResp resp = new LoginResp();
		resp.buser = buser;
		resp.token = token;
		return resp;
		
	}

	public BUser getBuser() {
		return buser;
	}

	public void setBuser(BUser buser) {
		this.buser = buser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
