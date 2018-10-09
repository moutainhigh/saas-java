package com.hq.storeManagerMS.service.muser.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserHandleHelper {
	public static MUserHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(MUserHandleHelper.class);
	}
	
	private Map<MUserUpdateType, IMUserHandle> handleMapper = new HashMap<MUserUpdateType, IMUserHandle>();

	public MUserHandleHelper() {
		handleMapper.put(MUserUpdateType.updateInfo, new IMUserHandle(){
			@Override
			public OperateTips update(MUserUpdateApiForm formInfo) {
				return UserHandle.getInstance().updateInfo(formInfo);
			}
		});
		handleMapper.put(MUserUpdateType.changePassword, new IMUserHandle(){
			@Override
			public OperateTips update(MUserUpdateApiForm formInfo) {
				return UserHandle.getInstance().changePassword(formInfo);
			}
		});
		handleMapper.put(MUserUpdateType.updMUserStatus, new IMUserHandle(){
			@Override
			public OperateTips update(MUserUpdateApiForm formInfo) {
				return UserHandle.getInstance().updMUserStatus(formInfo);
			}
		});
		
		handleMapper.put(MUserUpdateType.updRoleSet4Clerk, new IMUserHandle(){
			@Override
			public OperateTips update(MUserUpdateApiForm formInfo) {
				return MUserRoleHandle.getInstance().updRoleSet4Clerk(formInfo);
			}
		});
	}

	public OperateTips update(MUserUpdateApiForm formInfo) {
		MUserUpdateType updateType = MUserUpdateType.valueOf(formInfo.getUpdateType());
		IMUserHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
