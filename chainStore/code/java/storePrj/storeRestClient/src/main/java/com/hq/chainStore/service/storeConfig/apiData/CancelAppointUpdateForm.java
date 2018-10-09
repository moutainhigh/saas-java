package com.hq.chainStore.service.storeConfig.apiData;

public class CancelAppointUpdateForm {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointUpdateForm newInstance() {
		return new CancelAppointUpdateForm();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
