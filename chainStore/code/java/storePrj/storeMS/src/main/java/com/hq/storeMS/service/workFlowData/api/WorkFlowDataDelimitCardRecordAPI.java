package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataDelimitCardRecordHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/delimitCardRecord")
public class WorkFlowDataDelimitCardRecordAPI {
	
	@RequestMapping(value = "/{workFlowDataId}/{delimitCardId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("delimitCardId") String delimitCardId,
			@RequestBody DelimitCardRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataDelimitCardRecordHandler.getInstance().update(workFlowDataId, delimitCardId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{delimitCardId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("delimitCardId") String delimitCardId) {
		ReqResult<WorkFlowData> result = WorkFlowDataDelimitCardRecordHandler.getInstance().delete(workFlowDataId, delimitCardId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody DelimitCardRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataDelimitCardRecordHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody DelimitCardRecordAddListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataDelimitCardRecordHandler.getInstance().addList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> updList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody DelimitCardRecordUpdListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataDelimitCardRecordHandler.getInstance().updList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
