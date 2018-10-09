package com.hq.chainStore.service.storeConfig.apiData;

public class CancelAppointAddForm {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointAddForm newInstance() {
		return new CancelAppointAddForm();
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
