package com.hq.storeMS.service.schedule.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.schedule.apiData.ScheduleQueryForm;
import com.hq.storeMS.service.schedule.apiData.ScheduleUpdateStatusForm;
import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.schedule.data.ScheduleStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ScheduleHandler {

	
	public static ScheduleHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduleHandler.class);
	}
	
	private final LogModule logModule = LogModule.Schedule;
	
	public ReqResult<Schedule> findScheduleList(ScheduleQueryForm queryForm) {
		ReqResult<Schedule> result = ReqResult.newInstance(false, Schedule.class);
		try {
			List<Schedule> schedule = ScheduleMgr.getInstance().findScheduleList(queryForm);
			result.setTargetList(schedule);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Schedule, "SchedulHandler[findScheduleList]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Schedule> updateSchedule(long scheduleId, ScheduleUpdateStatusForm inputForm) {
		ReqResult<Schedule> result = ReqResult.newInstance(false, Schedule.class);
		try{
			boolean success = false;
			success = updateStatu(scheduleId, inputForm);
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips(inputForm.getStatuEnum().getMark()+"失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "ScheduleHandler[updateSchedule]";
			final String reason = LogHelper.getInstance().exceptionReason(scheduleId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
														.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public boolean updateStatu(long scheduleId, ScheduleUpdateStatusForm inputForm){
		Schedule schedule = ScheduleMgr.getInstance().get(scheduleId);
		ScheduleStatusEnum statusEnum = ScheduleStatusEnum.valueOf(inputForm.getStatu());
		schedule.setStatu(statusEnum.ordinal());
		ScheduleMgr.getInstance().update(schedule);
		return true;
	}

	public ReqResult<Schedule> getSchedule(long id) {
		ReqResult<Schedule> result = ReqResult.newInstance(false, Schedule.class);
		try {
			Schedule schedule = ScheduleMgr.getInstance().getSimple(id);
			if (schedule != null) {
				result.setTarget(schedule);
				result.setSuccess(true);
			} else {
				result.setTips("获取待办事项详情失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Schedule, "ScheduleHandler[get]", reason, e);
		}
		return result;
	}
}
