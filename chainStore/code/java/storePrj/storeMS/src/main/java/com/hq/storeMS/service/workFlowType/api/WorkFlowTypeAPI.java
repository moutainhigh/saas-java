package com.hq.storeMS.service.workFlowType.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowType.apiData.AddWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.apiData.UpdWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeHandler;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;

@RestController
@RequestMapping(value = "/workFlowType")
public class WorkFlowTypeAPI {
	
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<WorkFlowType>> findByCond(
    		@RequestParam(value="pageItemCount")int pageItemCount,
    		@RequestParam(value="pageNo")int pageNo){
		QueryWorkFlowTypeForm queryForm = QueryWorkFlowTypeForm.newInstance();
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		ReqResult<WorkFlowType> result = WorkFlowTypeHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<WorkFlowType>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findByName" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<WorkFlowType>> findByName(@RequestParam("typeName") String typeName){  
		ReqResult<WorkFlowType> result = WorkFlowTypeHandler.getInstance().findByName(typeName);
		ResponseEntity<RestResp<WorkFlowType>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/{workFlowTypeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowType>> get(
			@PathVariable("workFlowTypeId") long workFlowTypeId) {
		ReqResult<WorkFlowType> result = WorkFlowTypeHandler.getInstance().getWorkFlowType(workFlowTypeId);
		ResponseEntity<RestResp<WorkFlowType>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowTypeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowType>> update(
			@PathVariable("workFlowTypeId") long workFlowTypeId,
			@RequestBody UpdWorkFlowTypeForm inputForm) {
		ReqResult<WorkFlowType> result = WorkFlowTypeHandler.getInstance().updateWorkFlowType(workFlowTypeId, inputForm);
		ResponseEntity<RestResp<WorkFlowType>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowType>> add(
			@RequestBody AddWorkFlowTypeForm inputForm) {
		ReqResult<WorkFlowType> result = WorkFlowTypeHandler.getInstance().addWorkFlowType(inputForm);
		ResponseEntity<RestResp<WorkFlowType>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
