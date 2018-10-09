package com.hq.storeMS.service.arrearage.apiData;

public class ArrearageUpdateApiForm {
	private long storeId;
	private int updateType;

	private ArrearageAddPaymentHistoryApiForm addPaymentHistoryApiForm;

	public static ArrearageUpdateApiForm newInstance() {
		return new ArrearageUpdateApiForm();
	}

	public ArrearageUpdateType getUpdateTypeEnum() {
		return ArrearageUpdateType.valueOf(updateType);
	}

	public void setUpdateTypeEnum(ArrearageUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ArrearageAddPaymentHistoryApiForm getAddPaymentHistoryApiForm() {
		return addPaymentHistoryApiForm;
	}

	public void setAddPaymentHistoryApiForm(
			ArrearageAddPaymentHistoryApiForm addPaymentHistoryApiForm) {
		this.addPaymentHistoryApiForm = addPaymentHistoryApiForm;
	}

}
