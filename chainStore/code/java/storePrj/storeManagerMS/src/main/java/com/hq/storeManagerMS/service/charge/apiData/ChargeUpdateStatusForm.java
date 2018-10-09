package com.hq.storeManagerMS.service.charge.apiData;

import com.hq.storeManagerMS.service.charge.data.Charge;

public class ChargeUpdateStatusForm {
	private long id;
	private int status;

	public static ChargeUpdateStatusForm newInstance() {
		ChargeUpdateStatusForm instance = new ChargeUpdateStatusForm();
		return instance;
	}

	public void updateCharge(Charge data) {
		data.setStatus(status);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
