package com.hq.storeMS.service.appointment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeMS.service.appointment.bs.AppointmentHandler;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.appointment.data.AppointmentDateGroup;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentAPI {

	@RequestMapping(value = "/findLeaguerAppointmentList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> findLeaguerAppointmentList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "origin", defaultValue = "0") int origin,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "sort", defaultValue = "-1") int sort,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerName", defaultValue = "") String leaguerName,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setBuserId(buserId).setOrigin(origin).setStatus(status)
			.setStoreId(storeId).setLeaguerId(leaguerId).setLeaguerName(leaguerName).setSort(sort)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<Appointment> result = AppointmentHandler.getInstance().findAppointmentList(queryForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	
	@RequestMapping(value = "/findAppointmentList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> findAppointmentList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "origin", defaultValue = "0") int origin,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "sort", defaultValue = "-1") int sort,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerName", defaultValue = "") String leaguerName,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setBuserId(buserId).setOrigin(origin).setStatus(status)
			.setStoreId(storeId).setLeaguerId(leaguerId).setLeaguerName(leaguerName).setSort(sort)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<Appointment> result = AppointmentHandler.getInstance().findAppointmentList(queryForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getAppointmentPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getAppointmentPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "origin", defaultValue = "0") int origin,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "sort", defaultValue = "-1") int sort,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerName", defaultValue = "") String leaguerName,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setBuserId(buserId).setOrigin(origin).setStatus(status)
			.setStoreId(storeId).setLeaguerId(leaguerId).setLeaguerName(leaguerName).setSort(sort)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = AppointmentHandler.getInstance().getAppointmentPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findAppointmentDateGroupList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<AppointmentDateGroup>> findAppointmentDateGroupList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "origin", defaultValue = "0") int origin,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "sort", defaultValue = "-1") int sort,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerName", defaultValue = "") String leaguerName,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setBuserId(buserId).setOrigin(origin).setStatus(status)
			.setStoreId(storeId).setLeaguerId(leaguerId).setLeaguerName(leaguerName).setSort(sort)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<AppointmentDateGroup> result = AppointmentHandler.getInstance().findAppointmentDateGroupList(queryForm);
		ResponseEntity<RestResp<AppointmentDateGroup>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{appointmentId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> getAppointment(
			@PathVariable("storeId") long storeId,
			@PathVariable("appointmentId") long appointmentId) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().getAppointment(storeId, appointmentId);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}/{appointmentId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> deleteAppointment(
			@PathVariable("storeId") long storeId,
			@PathVariable("appointmentId") long appointmentId) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().deleteAppointment(storeId, appointmentId);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> getAppointment(
			@PathVariable("appointmentId") long appointmentId) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().getAppointment(0L, appointmentId);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> deleteAppointment(
			@PathVariable("appointmentId") long appointmentId) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().deleteAppointment(0L, appointmentId);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> updateAppointment(
			@PathVariable("appointmentId") long appointmentId,
			@RequestBody AppointmentUpdateApiForm inputForm) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().updateAppointment(appointmentId, inputForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> newAppointment(
			@RequestBody AppointmentAddApiForm inputForm) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().addAppointment(inputForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/********************************* customer ******************************************/
	@RequestMapping(value = "/addAppointmentForCuser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> addAppointmentForCuser(
			@RequestBody AppointmentAddApiForm inputForm) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().addAppointmentForCuser(inputForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
