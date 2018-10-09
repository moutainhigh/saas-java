package com.hq.copyData.copyModule.buser;

import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.LoginResp;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class BUserLoginHelper {
	private static BUserLoginHelper instance = new BUserLoginHelper();
	
	public static BUserLoginHelper getInstance(){
		return instance;
	}
	
	public BUser login(long phone){
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		loginForm.setPhone(phone).setPassword(DataConstants.PASSWORD);
		RestResp restResp = BUserMgr.getInstance().login(loginForm);
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		BUser tmpUser = loginResp.getBuser();
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(tmpUser.getId(), token);
		return tmpUser;
	}
}
