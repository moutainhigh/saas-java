package com.hq.storeMS.service.buser.apiData;

import com.hq.storeMS.service.buser.data.BUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LoginResp {

	private BUser buser;
	
	private String token;
	
	public static LoginResp newInstance(BUser buser,String token){
		LoginResp resp = new LoginResp();
		resp.buser = (BUser)buser;
		resp.token = token;
		return resp;
	}

	public BUser getBuser() {
		return buser;
	}

	public void setBuser(BUser buser) {
		this.buser = (BUser)buser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
