package com.hq.storeManagerMS.service.muser.apiData;

import com.hq.storeManagerMS.service.muser.data.MUser;
import com.hq.storeManagerMS.service.muser.data.MUserRO;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LoginResp {

	private MUser muser;

	private String token;

	public static LoginResp newInstance(MUserRO muser, String token) {
		LoginResp resp = new LoginResp();
		resp.muser = (MUser) muser;
		resp.token = token;
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
