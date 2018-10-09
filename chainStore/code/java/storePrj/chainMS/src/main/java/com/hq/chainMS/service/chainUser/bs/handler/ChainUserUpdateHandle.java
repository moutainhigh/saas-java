package com.hq.chainMS.service.chainUser.bs.handler;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.auth.chainUser.ChainUserPwdHelper;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ClerkInfo;
import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateInfoForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateType;
import com.hq.chainMS.service.chainUser.apiData.ChangePasswordForm;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserUpdateHandle {
	
	public static ChainUserUpdateHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ChainUserUpdateHandle.class);
	}

	public OperateTips updateInfo(ChainUserUpdateInfoForm inputForm){
		ChainUserAuthUtils.getInstance().checkChainUser(inputForm.getId());
		OperateTips tips = OperateTips.newInstance(false, ChainUserUpdateType.UpdateInfo.getDescript()+"失败");
		ChainUser chainUser = ChainUserMgr.getInstance().get(inputForm.getId());
		FastBeanCopyer.getInstance().copy(inputForm, chainUser);
		ChainUserMgr.getInstance().update(chainUser);
		
		Set<Long> chainIds = chainUser.takeChainIds();
		for (Long chainId : chainIds) {
			ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
			Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
			// TODO 有待优化  目前每次更新User信息  都同步更新Clerk信息
			if(clerkInfoMap.containsKey(chainUser.getId())) {
				ClerkInfo clerkInfo = clerkInfoMap.get(chainUser.getId());
				FastBeanCopyer.getInstance().copy(chainUser, clerkInfo);
				ChainClerkMgr.getInstance().update(chainClerk);
			}
		}
		
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips changePassword(ChangePasswordForm inputForm){
		ChainUserAuthUtils.getInstance().checkChainUser(inputForm.getId());
		OperateTips tips = OperateTips.newInstance(false, ChainUserUpdateType.ChangePassword.getDescript()+"失败");
		ChainUser chainUser = ChainUserMgr.getInstance().get(inputForm.getId());
		if(checkOldPassword(chainUser, inputForm.getOldPassword())){
			ChainUserMgr.getInstance().changePassword(chainUser, inputForm.getPassword());
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkOldPassword(ChainUser chainUser,String oldPassword){
		String encryptPassword = ChainUserPwdHelper.getInstance().getEncryptPassword(chainUser, oldPassword);
		if(StringUtils.equals(encryptPassword, chainUser.getPassword())){
			return true;
		}else{
			return false;
		}
	}
}
