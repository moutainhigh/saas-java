package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddByAppoint;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddByLeaguer;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataSwitchLeaguer;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataHandler;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataSaveHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData")
public class WorkFlowDataAPI {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findWorkFlowDataPageInfo" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<PageResp>> findWorkFlowDataPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "workFlowTypeId", defaultValue = "0") long workFlowTypeId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value="storeId") long storeId,
			@RequestParam(value="pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value="pageNo", defaultValue = "1") int pageNo){
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStatus(status)
			.setWorkFlowTypeId(workFlowTypeId).setBuserId(buserId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = WorkFlowDataHandler.getInstance().findWorkFlowDataPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> get(
			@PathVariable("workFlowDataId") long workFlowDataId) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().getWorkFlowData(0L, workFlowDataId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}/{workFlowDataId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> get(
			@PathVariable("storeId") long storeId,
			@PathVariable("workFlowDataId") long workFlowDataId) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().getWorkFlowData(storeId, workFlowDataId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody WorkFlowDataUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().updateWorkFlowData(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/addByAppoint", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addByAppoint(
			@RequestBody WorkFlowDataAddByAppoint inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().addByAppoint(inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> saveOrUpdate(
			@RequestBody WorkFlowDataSaveForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataSaveHandler.getInstance().saveOrUpdate(inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/********************************遗留的方法********************************/	
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<WorkFlowData>> findByCond(
    		@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "workFlowTypeId", defaultValue = "0") long workFlowTypeId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
    		@RequestParam(value="storeId") long storeId,
    		@RequestParam(value="pageItemCount", defaultValue = "0") int pageItemCount,
    		@RequestParam(value="pageNo", defaultValue = "1") int pageNo){
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStatus(status)
			.setWorkFlowTypeId(workFlowTypeId).setBuserId(buserId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/addByLeaguer", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addByLeaguer(
			@RequestBody WorkFlowDataAddByLeaguer inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().addByLeaguer(inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/switchLeaguer", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> switchLeaguer(
			@RequestBody WorkFlowDataSwitchLeaguer inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().switchLeaguer(inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().deleteWorkFlowData(workFlowDataId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@RequestBody WorkFlowDataAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataHandler.getInstance().addWorkFlowData(inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
