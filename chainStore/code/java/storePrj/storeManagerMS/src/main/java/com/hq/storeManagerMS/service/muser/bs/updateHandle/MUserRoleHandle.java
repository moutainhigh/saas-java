package com.hq.storeManagerMS.service.muser.bs.updateHandle;

import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateRoleSetApiData;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateType;
import com.hq.storeManagerMS.service.muser.bs.MUserMgr;
import com.hq.storeManagerMS.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserRoleHandle {
	public static MUserRoleHandle getInstance() {
		return HotSwap.getInstance().getSingleton(MUserRoleHandle.class);
	}
	
	//给用户授权
	public OperateTips updRoleSet4Clerk(MUserUpdateApiForm formInfo) {
		MUserUpdateRoleSetApiData inputData = formInfo.getUpdateRoleSetApiData();
		OperateTips operateTips = OperateTips.newInstance(false, MUserUpdateType.updRoleSet4Clerk.getDescript()+"失败");
		
		MUser data = MUserMgr.getInstance().get(inputData.getMuserId());
		if(data != null){
			inputData.updateMUser(data);
			MUserMgr.getInstance().update(data);
			operateTips.setSuccess(true);
		}else{
			operateTips.setTips("用户不存在,授权失败");
		}
		return operateTips;
	}
}
