package com.hq.customerRestClient.service.appointment.apiData;

public class AppointmentUpdateStatusApiForm {
	private int status;

	public static AppointmentUpdateStatusApiForm newInstance() {
		return new AppointmentUpdateStatusApiForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
