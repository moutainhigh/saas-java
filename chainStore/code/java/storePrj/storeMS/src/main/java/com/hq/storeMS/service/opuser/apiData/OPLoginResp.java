package com.hq.storeMS.service.opuser.apiData;

import com.hq.storeMS.service.opuser.data.OPUser;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OPLoginResp {

	private OPUser opuser;
	
	private String token;
	
	public static OPLoginResp newInstance(OPUser opuser,String token){
		OPLoginResp resp = new OPLoginResp();
		resp.opuser = (OPUser)opuser;
		resp.token = token;
		return resp;
		
	}


	public OPUser getOpuser() {
		return opuser;
	}

	public void setOpuser(OPUser opuser) {
		this.opuser = (OPUser)opuser;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
