package com.hq.chainStore.service.incomePay.apiData;

public class IncomePayUpdateApiForm {
	private long storeId;

	/**
	 * {@link IncomePayUpdateType}
	 */
	private int updateType;

	private IncomePayUpdateInfoForm incomePayUpdateInfoForm;

	public static IncomePayUpdateApiForm newInstance() {
		return new IncomePayUpdateApiForm();
	}

	public IncomePayUpdateType getUpdateTypeEnum() {
		return IncomePayUpdateType.valueOf(updateType);
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

	public IncomePayUpdateInfoForm getIncomePayUpdateInfoForm() {
		return incomePayUpdateInfoForm;
	}

	public void setIncomePayUpdateInfoForm(IncomePayUpdateInfoForm incomePayUpdateInfoForm) {
		this.incomePayUpdateInfoForm = incomePayUpdateInfoForm;
	}

}
