package com.hq.storeManagerRestClient.service.charge.apiData;

public class ChargeUpdateApiForm {
	/**
	 * {@link ChargeUpdateType}
	 */
	private int updateType;

	private ChargeUpdateInfoForm chargeUpdateInfoForm;
	private ChargeUpdateStatusForm chargeUpdateStatusForm;
	private ChargeUpdatePayInfoForm chargeUpdatePayInfoForm;

	public static ChargeUpdateApiForm newInstance() {
		return new ChargeUpdateApiForm();
	}

	public ChargeUpdateType getUpdateTypeEnum() {
		return ChargeUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ChargeUpdateInfoForm getChargeUpdateInfoForm() {
		return chargeUpdateInfoForm;
	}

	public void setChargeUpdateInfoForm(ChargeUpdateInfoForm chargeUpdateInfoForm) {
		this.chargeUpdateInfoForm = chargeUpdateInfoForm;
	}

	public ChargeUpdateStatusForm getChargeUpdateStatusForm() {
		return chargeUpdateStatusForm;
	}

	public void setChargeUpdateStatusForm(ChargeUpdateStatusForm chargeUpdateStatusForm) {
		this.chargeUpdateStatusForm = chargeUpdateStatusForm;
	}

	public ChargeUpdatePayInfoForm getChargeUpdatePayInfoForm() {
		return chargeUpdatePayInfoForm;
	}

	public void setChargeUpdatePayInfoForm(ChargeUpdatePayInfoForm chargeUpdatePayInfoForm) {
		this.chargeUpdatePayInfoForm = chargeUpdatePayInfoForm;
	}

}
