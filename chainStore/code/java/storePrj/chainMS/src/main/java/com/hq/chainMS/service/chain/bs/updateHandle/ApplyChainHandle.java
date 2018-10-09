package com.hq.chainMS.service.chain.bs.updateHandle;

import java.util.List;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.buserMessage.bs.BUserMessageMgr;
import com.hq.chainMS.service.chain.apiData.ApplyChainBatchDoForm;
import com.hq.chainMS.service.chain.apiData.ApplyChainDoForm;
import com.hq.chainMS.service.chain.apiData.ApplyChainForm;
import com.hq.chainMS.service.chain.apiData.ChainUpdateType;
import com.hq.chainMS.service.chain.bs.ChainMgr;
import com.hq.chainMS.service.chain.data.ApplyStatusEnum;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chain.data.ChainBeanHelper;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.store.bs.StoreMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ApplyChainHandle {

	public static ApplyChainHandle getInstance(){
		return HotSwap.getInstance().getSingleton(ApplyChainHandle.class);
	}
	
	public OperateTips applyChain(ApplyChainForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(inputForm.getChainId(), AdminPermEnum.CHAIN_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainUpdateType.ApplyChain.getDescript()+"失败");
		Chain chain = ChainMgr.getInstance().get(inputForm.getChainId());
		
		if(ChainBeanHelper.getInstance().applyChain(chain, inputForm.getStoreId())) {
			ChainMgr.getInstance().updateChain(chain);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips handleApplyChain(ApplyChainDoForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(inputForm.getChainId(), AdminPermEnum.CHAIN_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainUpdateType.HandleApplyChain.getDescript()+"失败");
		Chain chain = ChainMgr.getInstance().get(inputForm.getChainId());
		if(ChainBeanHelper.getInstance().handleApplyChain(chain, inputForm.getStoreId(), inputForm.getStatus())) {
			ChainMgr.getInstance().updateChain(chain);
			
			if(inputForm.getStatus() == ApplyStatusEnum.PASS.ordinal()) {
				StoreMgr.getInstance().joinChainHandler(inputForm.getStoreId(), inputForm.getChainId());
				BUserMessageMgr.getInstance().passApplyChain(inputForm.getStoreId(), inputForm.getChainId());
			}
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips batchHandleApplyChain(ApplyChainBatchDoForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(inputForm.getChainId(), AdminPermEnum.CHAIN_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainUpdateType.BatchHandleApplyChain.getDescript()+"失败");
		List<ApplyChainDoForm> list = inputForm.toApplyChainDoForms();
		for (ApplyChainDoForm applyChainDoForm : list) {
			handleApplyChain(applyChainDoForm);
		}
		tips.setSuccess(true);
		return tips;
	}
	
}
