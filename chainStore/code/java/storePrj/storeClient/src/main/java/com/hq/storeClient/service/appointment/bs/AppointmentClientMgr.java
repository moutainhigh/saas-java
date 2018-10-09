package com.hq.storeClient.service.appointment.bs;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.hq.storeClient.service.appointment.data.AppointmentDAO;
import com.hq.storeClient.service.appointment.data.AppointmentDateGroup;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class AppointmentClientMgr {

	public static AppointmentClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentClientMgr.class);
	}
	
	public PageResp<Appointment> getAppointmentPageInfo(AppointmentQueryForm queryForm) {
		final String findPath = "getAppointmentPageInfo";
		return AppointmentDAO.getInstance().getAppointmentPageInfo(findPath, queryForm);
	}
	
	public List<AppointmentDateGroup> findAppointmentDateGroupList(AppointmentQueryForm queryForm) {
		final String findPath = "findAppointmentDateGroupList";
		return AppointmentDAO.getInstance().findAppointmentDateGroupList(findPath, queryForm);
	}
	
	public Appointment getAppointment(long storeId, long appointmentId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, appointmentId);
		return AppointmentDAO.getInstance().findOne(uriPath);
	}
	
	public void deleteAppointment(long storeId, long appointmentId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, appointmentId);
		AppointmentDAO.getInstance().delete(uriPath);
	}
	
	public void updateAppointment(long appointmentId, AppointmentUpdateApiForm updateForm) {
		AppointmentDAO.getInstance().update(appointmentId, updateForm);
	}

	public Appointment addAppointment(AppointmentAddApiForm formInfo) {
		return AppointmentDAO.getInstance().add(formInfo);
	}
	
	public Appointment addAppointmentForCuser(AppointmentAddApiForm formInfo) {
		final String path = "addAppointmentForCuser";
		RestResp restResp = AppointmentDAO.getInstance().rawReq(path, formInfo);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), Appointment.class);
	}
}
