package com.hq.customerMS.service.appointment.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentMgr {

	public static AppointmentMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentMgr.class);
	}

	public Appointment addAppointment(AppointmentAddApiForm formInfo) {
		return AppointmentDataHolder.getInstance().addAppointment(formInfo);
	}

	public void updateAppointment(long appointmentId, AppointmentUpdateApiForm updateForm) {
		AppointmentDataHolder.getInstance().updateAppointment(appointmentId, updateForm);
	}

	public Appointment getAppointment(long storeId, long id) {
		return AppointmentDataHolder.getInstance().get(storeId, id);
	}

	public PageResp<Appointment> findPage(AppointmentQueryForm queryForm) {
		return AppointmentDataHolder.getInstance().findPage(queryForm);
	}

}
