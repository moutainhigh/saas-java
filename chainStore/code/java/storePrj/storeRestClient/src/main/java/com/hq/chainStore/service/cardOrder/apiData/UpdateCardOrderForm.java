package com.hq.chainStore.service.cardOrder.apiData;

public class UpdateCardOrderForm {
	private long storeId;
	private int updateType;

	private PayCardOrderForm payCardOrder;
	private CancelCardOrderForm cancelCardOrder;

	public static UpdateCardOrderForm newInstance() {
		return new UpdateCardOrderForm();
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

	public PayCardOrderForm getPayCardOrder() {
		return payCardOrder;
	}

	public void setPayCardOrder(PayCardOrderForm payCardOrder) {
		this.payCardOrder = payCardOrder;
	}

	public CancelCardOrderForm getCancelCardOrder() {
		return cancelCardOrder;
	}

	public void setCancelCardOrder(CancelCardOrderForm cancelCardOrder) {
		this.cancelCardOrder = cancelCardOrder;
	}
}
