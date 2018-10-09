package com.hq.chainStore.service.message.apiData;

public class BUserMessageUpdateForm {
	private int updateType;

	private BUserMessageUpdateStatusForm updateStatusData;

	public static BUserMessageUpdateForm newInstance() {
		return new BUserMessageUpdateForm();
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

}
