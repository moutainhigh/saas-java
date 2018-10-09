package com.hq.chainStore.service.buser.bs;

import com.hq.chainStore.service.buser.apiData.BUserResetPasswordData;
import com.hq.chainStore.service.buser.data.BUserResetPasswordDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

public class BUserResetPasswordMgr {

	public static BUserResetPasswordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserResetPasswordMgr.class);
	}
	
	//忘记密码   //未登录状态修改密码
	public RestResp resetPassword(BUserResetPasswordData resetPasswordData) {
		
		final String actionName = "changePassword";
		RestResp resp = BUserResetPasswordDAO.getInstance().rawReq(actionName , resetPasswordData);
		return resp;
	}
	
	
}
