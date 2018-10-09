package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataProductHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/product")
public class WorkFlowDataProductAPI {
	@RequestMapping(value = "/{workFlowDataId}/{productId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("productId") String productId,
			@RequestBody ProdRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataProductHandler.getInstance().update(workFlowDataId, productId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{productId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("productId") String productId) {
		ReqResult<WorkFlowData> result = WorkFlowDataProductHandler.getInstance().delete(workFlowDataId, productId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody ProdRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataProductHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody ProdRecordAddListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataProductHandler.getInstance().addList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> updList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody ProdRecordUpdListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataProductHandler.getInstance().updList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
