package com.hq.chainStore.service.schedule.bs;

import java.util.List;

import com.hq.chainStore.service.schedule.apidata.ScheduleQueryParams;
import com.hq.chainStore.service.schedule.apidata.ScheduleUpdateStatusForm;
import com.hq.chainStore.service.schedule.data.Schedule;
import com.hq.chainStore.service.schedule.data.ScheduleDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ScheduleMgr {

	public static ScheduleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduleMgr.class);
	}
	
	public List<Schedule> findScheduleList(ScheduleQueryParams params){
		final String findPath = "findScheduleList";
		ReqMap  reqMap = ReqMap.newInstance();
		reqMap.add("storeId", params.getStoreId()).add("beauticianId", params.getBeauticianId())
		.add("leaguerId", params.getLeaguerId()).add("statu", params.getStatu());	
		return ScheduleDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo()) ;
	}
	
	public void updateScheduleStatus(long scheduleId,long storeId,ScheduleUpdateStatusForm updateStatusForm){
		Schedule schedule = Schedule.newInstance();
		schedule.setBeauticianId(scheduleId);
		schedule.setStoreId(storeId);
		schedule.setStatu(updateStatusForm.getStatu());
		
		updateSchedule(scheduleId, updateStatusForm);
		
	}
	
	public void updateSchedule(long scheduleId, ScheduleUpdateStatusForm updateStatusForm) {
		ScheduleDAO.getInstance().update(scheduleId, updateStatusForm);
	}

	public Schedule getSchedule(long scheduleId) {
		return ScheduleDAO.getInstance().get(scheduleId);
	}
	

}
