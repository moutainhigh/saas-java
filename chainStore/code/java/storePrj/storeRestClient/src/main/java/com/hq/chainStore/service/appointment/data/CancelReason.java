package com.hq.chainStore.service.appointment.data;

public class CancelReason {
	// 原因
	private String reason;
	// 备注
	private String remarks;

	public static CancelReason newInstance() {
		CancelReason data = new CancelReason();
		return data;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
