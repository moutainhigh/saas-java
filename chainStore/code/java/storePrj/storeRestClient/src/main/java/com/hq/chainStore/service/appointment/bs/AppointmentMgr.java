package com.hq.chainStore.service.appointment.bs;

import java.util.List;

import com.hq.chainStore.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentDeleteForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateInfoApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateStatusApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateType;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.appointment.data.AppointmentDAO;
import com.hq.chainStore.service.appointment.data.AppointmentDateGroup;
import com.hq.chainStore.service.appointment.data.AppointmentQueryParams;
import com.hq.chainStore.service.common.PageResp;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentMgr {

	public static AppointmentMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentMgr.class);
	}
	
	public PageResp<Appointment> getAppointmentPageInfo(AppointmentQueryParams params) {
		final String findPath = "getAppointmentPageInfo";
		return AppointmentDAO.getInstance().getAppointmentPageInfo(findPath, params);
	}
	
	public List<Appointment> findLeaguerAppointmentList(AppointmentQueryParams params) {
		final String findPath = "findLeaguerAppointmentList";
		return AppointmentDAO.getInstance().findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}
	
	public List<Appointment> findAppointmentList(AppointmentQueryParams params) {
		final String findPath = "findAppointmentList";
		return AppointmentDAO.getInstance().findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}
	
	public List<AppointmentDateGroup> findAppointmentDateGroupList(AppointmentQueryParams params) {
		final String findPath = "findAppointmentDateGroupList";
		return AppointmentDAO.getInstance().findAppointmentDateGroupList(findPath, params);
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
	
	public void updateAppointmentInfo(long appointmentId, long storeId, AppointmentUpdateInfoApiForm updateInfoForm){
		AppointmentUpdateApiForm updateForm = AppointmentUpdateApiForm.newInstance();
		updateForm.setUpdateTypeEnum(AppointmentUpdateType.UpdateInfo);
		updateForm.setStoreId(storeId);
		updateForm.setUpdateInfoData(updateInfoForm);
		
		updateAppointment(appointmentId, updateForm);
	}
	
	public void updateAppointmentStatus(long appointmentId, long storeId, AppointmentUpdateStatusApiForm updateStatusForm){
		AppointmentUpdateApiForm updateForm = AppointmentUpdateApiForm.newInstance();
		updateForm.setUpdateTypeEnum(AppointmentUpdateType.UpdateState);
		updateForm.setStoreId(storeId);
		updateForm.setUpdateStatusData(updateStatusForm);
		
		updateAppointment(appointmentId, updateForm);
	}
	
	public void deleteAppoint(long appointmentId, long storeId){
		AppointmentUpdateApiForm updateForm = AppointmentUpdateApiForm.newInstance();
		updateForm.setUpdateTypeEnum(AppointmentUpdateType.DeleteAppoint);
		updateForm.setStoreId(storeId);
		AppointmentDeleteForm deleteForm = AppointmentDeleteForm.newInstance();
		deleteForm.setAppointmentId(appointmentId);
		updateForm.setAppointmentDeleteForm(deleteForm);
		
		updateAppointment(appointmentId, updateForm);
	}

	public Appointment addAppointment(AppointmentAddApiForm formInfo) {
		Appointment appointment = AppointmentDAO.getInstance().add(formInfo);
		return appointment;
	}
	
	@Deprecated
	public Appointment getAppointment(long appointmentId) {
		return AppointmentDAO.getInstance().get(appointmentId);
	}	
	
	@Deprecated
	public void deleteAppointment(long appointmentId) {
		AppointmentDAO.getInstance().delete(appointmentId);
	}
}
