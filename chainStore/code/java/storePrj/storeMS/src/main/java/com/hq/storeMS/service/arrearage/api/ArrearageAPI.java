package com.hq.storeMS.service.arrearage.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.storeMS.service.arrearage.apiData.ArrearageUpdateApiForm;
import com.hq.storeMS.service.arrearage.bs.ArrearageHandler;
import com.hq.storeMS.service.arrearage.data.ArrearageGroup;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/arrearage")
public class ArrearageAPI {

	@RequestMapping(value = "/findArrearageGroupList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ArrearageGroup>> findArrearageGroupList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setStatus(status).setLeaguerId(leaguerId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<ArrearageGroup> result = ArrearageHandler.getInstance().findArrearageGroupList(queryForm);
		ResponseEntity<RestResp<ArrearageGroup>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getArrearageGroupPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getArrearageGroupPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setStatus(status).setLeaguerId(leaguerId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ArrearageHandler.getInstance().getArrearageGroupPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findArrearageList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Arrearage>> findArrearageList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setStatus(status).setLeaguerId(leaguerId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<Arrearage> result = ArrearageHandler.getInstance().findArrearageList(queryForm);
		ResponseEntity<RestResp<Arrearage>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getArrearagePageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getArrearagePageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setStatus(status).setLeaguerId(leaguerId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ArrearageHandler.getInstance().getArrearagePageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{arrearageId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Arrearage>> getArrearage(
			@PathVariable("storeId") long storeId,
			@PathVariable("arrearageId") long arrearageId) {
		ReqResult<Arrearage> result = ArrearageHandler.getInstance().getArrearage(storeId, arrearageId);
		ResponseEntity<RestResp<Arrearage>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{arrearageId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Arrearage>> getArrearage(
			@PathVariable("arrearageId") long arrearageId) {
		ReqResult<Arrearage> result = ArrearageHandler.getInstance().getArrearage(0L, arrearageId);
		ResponseEntity<RestResp<Arrearage>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{arrearageId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Arrearage>> updateArrearage(
			@PathVariable("arrearageId") long arrearageId,
			@RequestBody ArrearageUpdateApiForm inputForm) {
		ReqResult<Arrearage> result = ArrearageHandler.getInstance().updateArrearage(arrearageId, inputForm);
		ResponseEntity<RestResp<Arrearage>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
