package com.hq.chainMS.service.chainClerk.bs.updateHandle;

import java.util.List;
import java.util.Set;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.buser.bs.BUserMgr;
import com.hq.chainMS.service.buserMessage.bs.BUserMessageMgr;
import com.hq.chainMS.service.chainClerk.apiData.ChainAllotStoreForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainBatchAllotStoreForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateType;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainStoreUserRelative;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeClerk.bs.StoreClerkMgr;
import com.hq.storeClient.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkAllotHandle {

	public static ClerkAllotHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkAllotHandle.class);
	}

	// 分配
	public OperateTips allotStore(long chainId, ChainAllotStoreForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.AllotStore.getDescript() + "失败");

		ChainUser chainUser = ChainUserMgr.getInstance().get(inputForm.getUserId());
		if (chainUser != null) {
			Set<Long> storeIds = inputForm.getStoreIds();
			storeIds.remove(null);
			
			ChainStoreUserRelative chainStoreUserRelative = chainUser.getChainSUMap().get(chainId);
			chainStoreUserRelative.setStoreIds(storeIds);
			ChainUserMgr.getInstance().update(chainUser);
			
			BUserAddByChainForm formInfo = BUserAddByChainForm.newInstance();
			FastBeanCopyer.getInstance().copy(chainUser, formInfo);
			BUser buser = BUserMgr.getInstance().regByChainForm(formInfo);
			
			long buserId = buser.getId();
			for (Long storeId : storeIds) {
				ApplyClerkInfoData applyClerkInfoData = ApplyClerkInfoData.newInstance();
				applyClerkInfoData.setbUserId(buserId);
				applyClerkInfoData.setStoreId(storeId);
				try {
					StoreClerkMgr.getInstance().applyClerkInfo(storeId, applyClerkInfoData);
					BUserMessageMgr.getInstance().allotClerkFromChain(storeId, chainId, buser);
				} catch (Exception e) {
					MainLog.error(LogModule.ChainClerk, "ClerkAllotHandle[allotStore]", "分配失败", e);
				}
			}
			tips.setSuccess(true);
		} 
		return tips;
	}
	
	// 批量分配
	public OperateTips batchAllotStore(long chainId, ChainBatchAllotStoreForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.BatchAllotStore.getDescript() + "失败");
		
		List<ChainAllotStoreForm> list = inputForm.getChainAllotStoreForms();
		for (ChainAllotStoreForm chainAllotStoreForm : list) {
			ChainUser chainUser = ChainUserMgr.getInstance().get(chainAllotStoreForm.getUserId());
			if(chainUser!=null) {
				chainAllotStoreForm.getStoreIds().addAll(chainUser.takeStoreIds());
				allotStore(chainId, chainAllotStoreForm);
			}
		}
		
		tips.setSuccess(true);
		return tips;
	}
}
