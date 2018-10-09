package com.hq.chainStore.service.workFlowType.apiData;

public class UpdWorkFlowTypeForm {
	private int updateType;

	private UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm;

	public static UpdWorkFlowTypeForm newInstance() {
		return new UpdWorkFlowTypeForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public UpdWorkFlowTypeInfoForm getUpdWorkFlowTypeInfoForm() {
		return updWorkFlowTypeInfoForm;
	}

	public void setUpdWorkFlowTypeInfoForm(UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm) {
		this.updWorkFlowTypeInfoForm = updWorkFlowTypeInfoForm;
	}

}
