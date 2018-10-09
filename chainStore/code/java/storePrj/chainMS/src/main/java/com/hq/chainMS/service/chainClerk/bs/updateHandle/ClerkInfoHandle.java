package com.hq.chainMS.service.chainClerk.bs.updateHandle;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chain.bs.ChainMgr;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkAddForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkReomveForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateInfoForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateType;
import com.hq.chainMS.service.chainClerk.apiData.ClerkRoleSaveForm;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ChainClerkBeanHelper;
import com.hq.chainMS.service.chainClerk.data.ClerkInfo;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.chainUser.data.ChainUserBeanHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkInfoHandle {

	public static ClerkInfoHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkInfoHandle.class);
	}

	// 添加员工
	public OperateTips addClerk(long chainId, ChainClerkAddForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.AddClerk.getDescript() + "失败");
		long phone = inputForm.getPhone();
		String name = inputForm.getName();
		long userId = 0L;
		
		//暂时不用短信校验
//		tips =SmsHandleHelper.getInstance().checkSmsCode(phone, inputForm.getVerifyCode());
//		if(!tips.isSuccess()){
//			return tips;
//		}

		ChainUser chainUser = ChainUserMgr.getInstance().findByPhone(phone);
		if (chainUser == null) {
			ChainUser tmpChainUser = ChainUser.newInstance();
			FastBeanCopyer.getInstance().copy(inputForm, tmpChainUser);
			ChainUserBeanHelper.getInstance().addChainClerk(tmpChainUser, chainId);
			ChainUser newUser = ChainUserMgr.getInstance().createUser(tmpChainUser);
			userId = newUser.getId();
		} else {
			userId = chainUser.getId();
		}

		ClerkInfo clerkInfo = ClerkInfo.newInstance(userId, name, phone);
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		if (ChainClerkBeanHelper.getInstance().addClerkInfo(chainClerk, clerkInfo)) {
			ChainClerkMgr.getInstance().update(chainClerk);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 修改员工信息
	public OperateTips updateClerk(long chainId, ChainClerkUpdateInfoForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.UpdateClerk.getDescript() + "失败");
		
		ChainUser chainUser = ChainUserMgr.getInstance().get(inputForm.getId());
		inputForm.updateChainUser(chainUser);
		ChainUserMgr.getInstance().update(chainUser);
		
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		ClerkInfo clerkInfo = chainClerk.getClerkInfoMap().get(inputForm.getId());
		clerkInfo.setName(inputForm.getName());
		clerkInfo.setGender(inputForm.getGender());
		ChainClerkMgr.getInstance().update(chainClerk);
		
		tips.setSuccess(true);
		return tips;
	}

	// 删除员工
	public OperateTips reomveClerk(long chainId, ChainClerkReomveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.ReomveClerk.getDescript() + "失败");

		long clerkBUserId = inputForm.getUserId();
		if (isBoss(chainId, clerkBUserId)) {
			tips.setTips("老板不能删除");
			return tips;
		}

		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		if (ChainClerkBeanHelper.getInstance().removeClerk(chainClerk, clerkBUserId)) {
			ChainClerkMgr.getInstance().update(chainClerk);
			removeChainIdOfClerk(inputForm);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean isBoss(long chainId, long userId) {
		Chain chain = ChainMgr.getInstance().get(chainId);
		if (chain != null && chain.getBossId() == userId) {
			return true;
		}
		return false;
	}

	private void removeChainIdOfClerk(ChainClerkReomveForm reomveClerkData) {
		ChainUser chainUser = ChainUserMgr.getInstance().get(reomveClerkData.getUserId());
		chainUser.getChainSUMap().remove(reomveClerkData.getChainId());
		ChainUserMgr.getInstance().update(chainUser);
	}

	// 设置员工的岗位信息
	public OperateTips addRoleSet2Clerk(long chainId, ClerkRoleSaveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.SaveRoleSet2Clerk.getDescript() + "失败");

		if (isBoss(chainId, inputForm.getUserId())) {
			tips.setTips("老板不能重新分配岗位");
			return tips;
		}
		
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		long userId = inputForm.getUserId();
		if (ChainClerkBeanHelper.getInstance().addRoleSet2Clerk(chainClerk, userId, inputForm.getRoleIds())) {
			ChainClerkMgr.getInstance().update(chainClerk);
			tips.setSuccess(true);
		}
		return tips;
	}
}
