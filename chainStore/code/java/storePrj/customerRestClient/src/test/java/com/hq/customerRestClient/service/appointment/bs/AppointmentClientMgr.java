package com.hq.customerRestClient.service.appointment.bs;

import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.hq.customerRestClient.service.appointment.data.Appointment;
import com.hq.customerRestClient.service.appointment.data.AppointmentDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentClientMgr {

	public static AppointmentClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentClientMgr.class);
	}
	
	public Appointment getAppointment(long storeId, long appointmentId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, appointmentId);
		return AppointmentDAO.getInstance().findOne(uriPath);
	}
}
