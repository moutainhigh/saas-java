package com.hq.customerMS.service.appointment.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.appointment.bs.AppointmentHandler;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.apiData.AppointmentUpdateApiForm;
import com.hq.storeClient.service.appointment.data.Appointment;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentAPI {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "cuserId") long cuserId, 
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setStoreId(storeId).setCuserId(cuserId).setStatus(status)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = AppointmentHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{appointmentId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> getAppointment(@PathVariable("storeId") long storeId,
			@PathVariable("appointmentId") long appointmentId) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().getAppointment(storeId, appointmentId);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> updateAppointment(@PathVariable("appointmentId") long appointmentId,
			@RequestBody AppointmentUpdateApiForm inputForm) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().updateAppointment(appointmentId, inputForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Appointment>> newAppointment(@RequestBody AppointmentAddApiForm inputForm) {
		ReqResult<Appointment> result = AppointmentHandler.getInstance().addAppointment(inputForm);
		ResponseEntity<RestResp<Appointment>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
