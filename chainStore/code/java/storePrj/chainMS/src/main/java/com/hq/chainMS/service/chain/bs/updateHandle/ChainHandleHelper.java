package com.hq.chainMS.service.chain.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chain.apiData.ChainUpdateForm;
import com.hq.chainMS.service.chain.apiData.ChainUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainHandleHelper{

	public static ChainHandleHelper getInstance(){
		return HotSwap.getInstance().getSingleton(ChainHandleHelper.class);
	}
	
	private Map<ChainUpdateType,IChainHandle> handleMapper = new HashMap<ChainUpdateType,IChainHandle>();
	
	public ChainHandleHelper(){
		handleMapper.put(ChainUpdateType.UpdateChainInfo, new IChainHandle() {
			@Override
			public OperateTips update(ChainUpdateForm updateForm) {
				return ChainUpdateHandle.getInstance().updateChainInfo(updateForm.getChainUpdateInfoForm());
			}
		});
		handleMapper.put(ChainUpdateType.RelieveStore, new IChainHandle() {
			@Override
			public OperateTips update(ChainUpdateForm updateForm) {
				return ChainUpdateHandle.getInstance().relieveStore(updateForm.getRelieveStoreForm());
			}
		});
		
		handleMapper.put(ChainUpdateType.ApplyChain, new IChainHandle() {
			@Override
			public OperateTips update(ChainUpdateForm updateForm) {
				return ApplyChainHandle.getInstance().applyChain(updateForm.getApplyChainForm());
			}
		});
		handleMapper.put(ChainUpdateType.HandleApplyChain, new IChainHandle() {
			@Override
			public OperateTips update(ChainUpdateForm updateForm) {
				return ApplyChainHandle.getInstance().handleApplyChain(updateForm.getApplyChainDoForm());
			}
		});
		handleMapper.put(ChainUpdateType.BatchHandleApplyChain, new IChainHandle() {
			@Override
			public OperateTips update(ChainUpdateForm updateForm) {
				return ApplyChainHandle.getInstance().batchHandleApplyChain(updateForm.getApplyChainBatchDoForm());
			}
		});
	}
	
	public OperateTips update(ChainUpdateForm updateForm){
		ChainUpdateType updateType = ChainUpdateType.valueOf(updateForm.getUpdateType());
		IChainHandle handle = handleMapper.get(updateType);
		if(handle != null){
			return handle.update(updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
	
}
