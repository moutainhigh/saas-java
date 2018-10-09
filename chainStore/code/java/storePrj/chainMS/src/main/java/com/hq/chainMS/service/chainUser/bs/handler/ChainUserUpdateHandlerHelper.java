package com.hq.chainMS.service.chainUser.bs.handler;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateType;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserUpdateHandlerHelper {

	public static ChainUserUpdateHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainUserUpdateHandlerHelper.class);
	}
	
	private Map<ChainUserUpdateType, IChainUserHandle> handleMapper = new HashMap<ChainUserUpdateType, IChainUserHandle>();

	public ChainUserUpdateHandlerHelper() {
		handleMapper.put(ChainUserUpdateType.UpdateInfo, new IChainUserHandle(){
			@Override
			public OperateTips update(ChainUserUpdateForm updateForm){
				return ChainUserUpdateHandle.getInstance().updateInfo(updateForm.getChainUserUpdateInfoForm());
			}
		});
		handleMapper.put(ChainUserUpdateType.ChangePassword, new IChainUserHandle(){
			@Override
			public OperateTips update(ChainUserUpdateForm updateForm){
				return ChainUserUpdateHandle.getInstance().changePassword(updateForm.getChangePasswordForm());
			}
		});
	}

	public OperateTips update(ChainUserUpdateForm updateForm) {
		ChainUserUpdateType updateType = updateForm.getChainUserUpdateType();
		IChainUserHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}

}
