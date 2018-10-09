package com.hq.customerMS.service.appointment.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.util.AppUtils;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.cuser.bs.CUserQueryMgr;
import com.hq.customerMS.service.cuser.data.CUser;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentHandler {

	public static AppointmentHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentHandler.class);
	}
	
	private final LogModule logModule = LogModule.Appointment;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPage(AppointmentQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			CUser user = CUserQueryMgr.getInstance().get(queryForm.getCuserId());
			queryForm.setLeaguerId(AppUtils.joinByUnderline(queryForm.getStoreId(), user.getId()));
			PageResp<Appointment> page = AppointmentMgr.getInstance().findPage(queryForm);
			result.setTarget(page);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppointmentHandler[findPage]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Appointment> getAppointment(long storeId, long appointmentId) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			Appointment appointment = AppointmentMgr.getInstance().getAppointment(storeId, appointmentId);
			result.setTarget(appointment);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppointmentHandler[getAppointment]";
			final String reason = LogHelper.getInstance().exceptionReason(appointmentId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<Appointment> updateAppointment(long appointmentId, AppointmentUpdateApiForm updateForm) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			AppointmentMgr.getInstance().updateAppointment(appointmentId, updateForm);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppointmentHandler[updateAppointment]";
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Appointment> addAppointment(AppointmentAddApiForm addInfo) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			CUser user = CUserQueryMgr.getInstance().get(addInfo.getCuserId());
			addInfo.setCreatorId(user.getId());
			addInfo.setCreatorName(user.getName());
			addInfo.setLeaguerId(AppUtils.joinByUnderline(addInfo.getStoreId(), user.getId()));
			Appointment appointment = AppointmentMgr.getInstance().addAppointment(addInfo);
			result.setTarget(appointment);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AppointmentHandler[addAppointment]";
			final String reason = LogHelper.getInstance().exceptionReason(addInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
