package com.hq.storeMS.service.workFlowData.apiData;

public class WorkFlowDataUpdateForm {
	private int updateType;

	private WorkFlowDataUpdateInfoForm updWorkFlowDataInfoForm;
	private WorkFlowDataUpdateStatusForm workFlowDataStatusForm;
	private WorkFlowDataUpdateBuyItemsForm workFlowDataBuyItemsForm;
	private WorkFlowDataDeleteForm workFlowDataDeleteForm;
	private WorkFlowDataCancelForm workFlowDataCancelForm;

	public static WorkFlowDataUpdateForm newInstance() {
		return new WorkFlowDataUpdateForm();
	}
	
	public WorkFlowDataUpdateEnum getWorkFlowDataUpdateEnum() {
		return WorkFlowDataUpdateEnum.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public WorkFlowDataUpdateInfoForm getUpdWorkFlowDataInfoForm() {
		return updWorkFlowDataInfoForm;
	}

	public void setUpdWorkFlowDataInfoForm(WorkFlowDataUpdateInfoForm updWorkFlowDataInfoForm) {
		this.updWorkFlowDataInfoForm = updWorkFlowDataInfoForm;
	}

	public WorkFlowDataUpdateStatusForm getWorkFlowDataStatusForm() {
		return workFlowDataStatusForm;
	}

	public void setWorkFlowDataStatusForm(WorkFlowDataUpdateStatusForm workFlowDataStatusForm) {
		this.workFlowDataStatusForm = workFlowDataStatusForm;
	}

	public WorkFlowDataUpdateBuyItemsForm getWorkFlowDataBuyItemsForm() {
		return workFlowDataBuyItemsForm;
	}

	public void setWorkFlowDataBuyItemsForm(WorkFlowDataUpdateBuyItemsForm workFlowDataBuyItemsForm) {
		this.workFlowDataBuyItemsForm = workFlowDataBuyItemsForm;
	}

	public WorkFlowDataDeleteForm getWorkFlowDataDeleteForm() {
		return workFlowDataDeleteForm;
	}

	public void setWorkFlowDataDeleteForm(WorkFlowDataDeleteForm workFlowDataDeleteForm) {
		this.workFlowDataDeleteForm = workFlowDataDeleteForm;
	}

	public WorkFlowDataCancelForm getWorkFlowDataCancelForm() {
		return workFlowDataCancelForm;
	}

	public void setWorkFlowDataCancelForm(WorkFlowDataCancelForm workFlowDataCancelForm) {
		this.workFlowDataCancelForm = workFlowDataCancelForm;
	}
}
