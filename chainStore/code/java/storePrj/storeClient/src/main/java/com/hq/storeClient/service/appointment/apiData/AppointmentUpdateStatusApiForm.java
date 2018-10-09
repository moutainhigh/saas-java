package com.hq.storeClient.service.appointment.apiData;

import com.hq.storeClient.service.appointment.data.CancelReason;

public class AppointmentUpdateStatusApiForm {
	private int status;
	// 取消原因
	private CancelReason cancelReason;
	
	/** =========================遗留的字段========================= **/
	private long operatorId;
	private String operatorName;

	public static AppointmentUpdateStatusApiForm newInstance() {
		return new AppointmentUpdateStatusApiForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

}
