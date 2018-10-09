package com.hq.storeMS.service.workFlowData.apiData;

public class ProdRecordUpdateForm {
	private int updateType;

	private ProdRecordUpdInfoForm prodRecordUpdInfoForm;
	private ProdRecordUpdStatusForm prodRecordUpdStatusForm;

	public static ProdRecordUpdateForm newInstance() {
		return new ProdRecordUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ProdRecordUpdInfoForm getProdRecordUpdInfoForm() {
		return prodRecordUpdInfoForm;
	}

	public void setProdRecordUpdInfoForm(
			ProdRecordUpdInfoForm prodRecordUpdInfoForm) {
		this.prodRecordUpdInfoForm = prodRecordUpdInfoForm;
	}

	public ProdRecordUpdStatusForm getProdRecordUpdStatusForm() {
		return prodRecordUpdStatusForm;
	}

	public void setProdRecordUpdStatusForm(
			ProdRecordUpdStatusForm prodRecordUpdStatusForm) {
		this.prodRecordUpdStatusForm = prodRecordUpdStatusForm;
	}
}
