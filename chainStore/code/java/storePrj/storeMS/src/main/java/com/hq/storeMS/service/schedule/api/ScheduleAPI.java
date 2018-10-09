package com.hq.storeMS.service.schedule.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.schedule.apiData.ScheduleQueryForm;
import com.hq.storeMS.service.schedule.apiData.ScheduleUpdateStatusForm;
import com.hq.storeMS.service.schedule.bs.ScheduleHandler;
import com.hq.storeMS.service.schedule.data.Schedule;

/**
 * 
 * ClassName: ScheduleAPI <br/>
 * Function: TODO 待办事项API<br/>
 * 
 * @author helen
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleAPI {

	@RequestMapping(value = "/findScheduleList", method = RequestMethod.GET, produces ="application/json")
	public ResponseEntity<RestResp<Schedule>> findScheduleList(
			@RequestParam(value = "statu", defaultValue="-1") Integer statu,
			@RequestParam(value = "leaguerId", defaultValue="") String leaguerId,
			@RequestParam(value = "storeId", defaultValue="0") Long storeId ,
			@RequestParam(value = "beauticianId", defaultValue="0") Long beauticianId,
			@RequestParam(value = "pageItemCount", defaultValue="0") Integer pageItemCount,
			@RequestParam(value = "pageNo", defaultValue="1") Integer pageNo){
		
 		ScheduleQueryForm queryForm = ScheduleQueryForm.newInstance();
		queryForm.setStoreId(storeId).setBeauticianId(beauticianId)
			.setPageItemCount(pageItemCount).setPageNo(pageNo)
			.setLeaguerId(leaguerId).setStatu(statu);
		
		ReqResult<Schedule> result = ScheduleHandler.getInstance().findScheduleList(queryForm);
		ResponseEntity<RestResp<Schedule>> respEntity = result.buildRespEntity();
		return respEntity;
		
	}
	
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Schedule>> updateSchedule(
			@PathVariable("scheduleId") long scheduleId,
			@RequestBody ScheduleUpdateStatusForm inputForm) {
		
		ReqResult<Schedule> result=ScheduleHandler.getInstance().updateSchedule(scheduleId, inputForm);
		ResponseEntity<RestResp<Schedule>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Schedule>> getSchedule(
			@PathVariable("scheduleId") long scheduleId) {
		ReqResult<Schedule> result = ScheduleHandler.getInstance().getSchedule(scheduleId);
		ResponseEntity<RestResp<Schedule>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	

}
