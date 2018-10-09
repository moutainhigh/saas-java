package com.hq.storeMS.service.message.apiData;

public class BUserMessageUpdateForm {
	private int updateType;

	private BUserMessageUpdateStatusForm updateStatusData;
	private BUserMessageBatchUpdateStatusForm batchUpdateStatusForm;

	public static BUserMessageUpdateForm newInstance() {
		return new BUserMessageUpdateForm();
	}

	public BUserMessageUpdateType getBUserMessageUpdateType() {
		return BUserMessageUpdateType.valueOf(updateType);
	}
	
	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public BUserMessageUpdateStatusForm getUpdateStatusData() {
		return updateStatusData;
	}

	public void setUpdateStatusData(BUserMessageUpdateStatusForm updateStatusData) {
		this.updateStatusData = updateStatusData;
	}

	public BUserMessageBatchUpdateStatusForm getBatchUpdateStatusForm() {
		return batchUpdateStatusForm;
	}

	public void setBatchUpdateStatusForm(BUserMessageBatchUpdateStatusForm batchUpdateStatusForm) {
		this.batchUpdateStatusForm = batchUpdateStatusForm;
	}

}
