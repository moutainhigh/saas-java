package com.hq.chainMS.service.chain.apiData;

public class ChainUpdateForm {
	private int updateType;
	
	private ChainUpdateInfoForm chainUpdateInfoForm;
	
	private ApplyChainForm applyChainForm;
	private ApplyChainDoForm applyChainDoForm;
	private ApplyChainBatchDoForm applyChainBatchDoForm;
	
	private RelieveStoreForm relieveStoreForm;

	public static ChainUpdateForm newInstance() {
		return new ChainUpdateForm();
	}
	
	public ChainUpdateType getChainUpdateType() {
		return ChainUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ChainUpdateInfoForm getChainUpdateInfoForm() {
		return chainUpdateInfoForm;
	}

	public void setChainUpdateInfoForm(ChainUpdateInfoForm chainUpdateInfoForm) {
		this.chainUpdateInfoForm = chainUpdateInfoForm;
	}

	public ApplyChainForm getApplyChainForm() {
		return applyChainForm;
	}

	public void setApplyChainForm(ApplyChainForm applyChainForm) {
		this.applyChainForm = applyChainForm;
	}

	public ApplyChainDoForm getApplyChainDoForm() {
		return applyChainDoForm;
	}

	public void setApplyChainDoForm(ApplyChainDoForm applyChainDoForm) {
		this.applyChainDoForm = applyChainDoForm;
	}

	public ApplyChainBatchDoForm getApplyChainBatchDoForm() {
		return applyChainBatchDoForm;
	}

	public void setApplyChainBatchDoForm(ApplyChainBatchDoForm applyChainBatchDoForm) {
		this.applyChainBatchDoForm = applyChainBatchDoForm;
	}

	public RelieveStoreForm getRelieveStoreForm() {
		return relieveStoreForm;
	}

	public void setRelieveStoreForm(RelieveStoreForm relieveStoreForm) {
		this.relieveStoreForm = relieveStoreForm;
	}

}
