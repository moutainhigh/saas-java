package com.hq.storeMS.service.appointment.bs;

import java.util.List;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateType;
import com.hq.storeMS.service.appointment.data.AppointFromEnums;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.appointment.data.AppointmentDateGroup;
import com.hq.storeMS.service.appointment.data.AppointmentStatusEnum;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.cuser.bs.CUserMgr;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentHandler {

	public static AppointmentHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentHandler.class);
	}
	
	private final LogModule logModule = LogModule.Appointment;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getAppointmentPageInfo(AppointmentQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Appointment> pageResp = AppointmentMgr.getInstance().getAppointmentPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Appointment, "AppointmentHandler[getAppointmentPageInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Appointment> findAppointmentList(AppointmentQueryForm queryForm) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			List<Appointment> appointment = AppointmentMgr.getInstance().findAppointmentList(queryForm);
			result.setTargetList(appointment);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Appointment, "AppointmentHandler[findAppointmentList]", reason, e);
		}
		return result;
	}
	
	public ReqResult<AppointmentDateGroup> findAppointmentDateGroupList(AppointmentQueryForm queryForm) {
		ReqResult<AppointmentDateGroup> result = ReqResult.newInstance(false, AppointmentDateGroup.class);
		try {
			List<AppointmentDateGroup> list = AppointmentMgr.getInstance().findAppointmentDateGroupList(queryForm);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Appointment, "AppointmentHandler[findAppointmentDateGroupList]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Appointment> getAppointment(long storeId, long id) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Appointment appointment = AppointmentMgr.getInstance().get(storeId, id);
			result.setTarget(appointment);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Appointment, "AppointmentHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<Appointment> updateAppointment(long appointmentId, AppointmentUpdateApiForm updateInfo) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
//			BUserAuthUtils.getInstance().checkPermission(updateInfo.getStoreId(), StoreAdminPermEnum.APPOINTMENT_ADMIN);
			AppointmentUpdateType updateType = AppointmentUpdateType.valueOf(updateInfo.getUpdateType());
			OperateTips operateTips = null;
			switch (updateType) {
				case UpdateInfo:
					operateTips = AppointmentMgr.getInstance().updateInfo(updateInfo.getStoreId(), appointmentId, updateInfo.getUpdateInfoData());
					break;
				case UpdateState:
					operateTips = AppointmentMgr.getInstance().updateState(updateInfo.getStoreId(), appointmentId,updateInfo.getUpdateStatusData());
					break;
				case DeleteAppoint:
					operateTips = AppointmentMgr.getInstance().deleteAppoint(updateInfo.getStoreId(), appointmentId, updateInfo.getAppointmentDeleteForm());
					break;
				default:
					break;
			}
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				result.setTips(operateTips.getTips());
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "AppointmentHandler[updateAppointment]";
			final String reason = LogHelper.getInstance().exceptionReason(appointmentId, updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	public ReqResult<Appointment> deleteAppointment(long storeId, long appointmentId) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Appointment appointment = AppointmentMgr.getInstance().get(storeId, appointmentId);
			if(appointment != null){
				AppointmentMgr.getInstance().delete(appointment);
				OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, appointment.getName(), OpLogTypeEnum.Appoint, "预约信息被删除"));
				result.setSuccess(true);
			}else{
				result.setTips("删除的预约记录不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "AppointmentHandler[deleteAppointment]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, appointmentId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Appointment> addAppointment(AppointmentAddApiForm addInfo) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			Appointment appointment = Appointment.newInstance();
			FastBeanCopyer.getInstance().copy(addInfo, appointment);
			//自建的预约  默认是已接受的状态
			appointment.setStatus(AppointmentStatusEnum.RECEIVE.ordinal());
			appointment.setOrigin(AppointFromEnums.STORE.ordinal());
			
			if(appointment.getCreatorId() == 0) {
				BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
				if(sessionBUser!=null) {
					appointment.setCreatorId(sessionBUser.getId());
					appointment.setCreatorName(sessionBUser.getName());
				}
			}
			
			AppointmentMgr.getInstance().addAndReturnId(appointment);
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
	
	public ReqResult<Appointment> addAppointmentForCuser(AppointmentAddApiForm addInfo) {
		ReqResult<Appointment> result = ReqResult.newInstance(false, Appointment.class);
		try {
			CUser cuser = CUserMgr.getInstance().get(addInfo.getCuserId());
			Appointment appointment = Appointment.newInstance();
			FastBeanCopyer.getInstance().copy(addInfo, appointment);
			appointment.setLeaguerId(Leaguer.genIdByStoreId(addInfo.getStoreId(), cuser.getId()));
			appointment.setOrigin(AppointFromEnums.CUSTOMER.ordinal());
			appointment.setCreatorId(cuser.getId());
			appointment.setCreatorName(cuser.getName());
			AppointmentMgr.getInstance().addAndReturnId(appointment);
			result.setTarget(appointment);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Appointment, "CmAppointmentHandler[addAppointmentForCuser]", reason, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
