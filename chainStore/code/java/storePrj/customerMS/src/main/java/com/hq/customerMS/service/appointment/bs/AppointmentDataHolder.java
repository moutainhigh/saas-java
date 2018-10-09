package com.hq.customerMS.service.appointment.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.appointment.data.AppointmentCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeClient.service.appointment.bs.AppointmentClientMgr;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentDataHolder {
	
	public static AppointmentDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AppointmentDataHolder.class);
	}
	
	public void updateAppointment(long appointmentId, AppointmentUpdateApiForm updateForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		AppointmentClientMgr.getInstance().updateAppointment(appointmentId, updateForm);
		AppIdThreadLocal.getInstance().set(null);
	}

	public Appointment addAppointment(AppointmentAddApiForm formInfo) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Appointment data = AppointmentClientMgr.getInstance().addAppointmentForCuser(formInfo);
		AppIdThreadLocal.getInstance().set(null);
		return data;
		
	}
	
	public Appointment get(long storeId, long appointmentId) {
		Appointment data = AppointmentCacheDAO.getInstance().get(storeId, appointmentId);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = AppointmentClientMgr.getInstance().getAppointment(storeId, appointmentId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PageResp<Appointment> findPage(AppointmentQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Appointment> page = AppointmentClientMgr.getInstance().getAppointmentPageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}
}
