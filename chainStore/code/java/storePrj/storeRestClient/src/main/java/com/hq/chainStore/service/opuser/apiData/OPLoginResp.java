package com.hq.chainStore.service.opuser.apiData;

import com.hq.chainStore.service.opuser.data.OPUser;

public class OPLoginResp {

	private OPUser opuser;
	
	private String token;
	
	public static OPLoginResp newInstance(OPUser opuser,String token){
		OPLoginResp resp = new OPLoginResp();
		resp.opuser = opuser;
		resp.token = token;
		return resp;
		
	}
	

	public OPUser getOpuser() {
		return opuser;
	}


	public void setOpuser(OPUser opuser) {
		this.opuser = opuser;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
