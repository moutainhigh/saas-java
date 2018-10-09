package com.hq.chainMS.service.chain.bs.updateHandle;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chain.apiData.ChainUpdateInfoForm;
import com.hq.chainMS.service.chain.apiData.ChainUpdateType;
import com.hq.chainMS.service.chain.apiData.RelieveStoreForm;
import com.hq.chainMS.service.chain.bs.ChainMgr;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chain.data.OperateFromEnum;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.store.bs.StoreMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUpdateHandle {

	public static ChainUpdateHandle getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUpdateHandle.class);
	}
	
	public OperateTips updateChainInfo(ChainUpdateInfoForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(inputForm.getChainId(), AdminPermEnum.CHAIN_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainUpdateType.UpdateChainInfo.getDescript()+"失败");
		Chain chain = ChainMgr.getInstance().get(inputForm.getChainId());
		if(chain!=null){
			inputForm.update(chain);
			ChainMgr.getInstance().updateChain(chain);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips relieveStore(RelieveStoreForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(inputForm.getChainId(), AdminPermEnum.CHAIN_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainUpdateType.RelieveStore.getDescript()+"失败");
		Chain chain = ChainMgr.getInstance().get(inputForm.getChainId());
		if(chain!=null){
			chain.getChainStoreMap().remove(inputForm.getStoreId());
			ChainMgr.getInstance().updateChain(chain);
			
			if(inputForm.getOperateFrom() == OperateFromEnum.chainMS.ordinal()) {
				StoreMgr.getInstance().relieveStore(inputForm.getStoreId(), inputForm.getChainId());
			}
			tips.setSuccess(true);
		}
		return tips;
	}
	
}
