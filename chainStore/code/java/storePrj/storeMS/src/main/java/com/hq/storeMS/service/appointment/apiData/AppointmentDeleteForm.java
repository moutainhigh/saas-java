package com.hq.storeMS.service.appointment.apiData;

public class AppointmentDeleteForm {
	// 店铺ID
	private long appointmentId;
	
	public static AppointmentDeleteForm newInstance() {
		return new AppointmentDeleteForm();
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

}
