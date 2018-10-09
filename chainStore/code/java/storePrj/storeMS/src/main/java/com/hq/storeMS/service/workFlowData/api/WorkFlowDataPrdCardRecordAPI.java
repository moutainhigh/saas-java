package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataPrdCardRecordHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/prdCard")
public class WorkFlowDataPrdCardRecordAPI {
	@RequestMapping(value = "/{workFlowDataId}/{prdCardId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("prdCardId") String prdCardId,
			@RequestBody PrdCardUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPrdCardRecordHandler.getInstance().update(workFlowDataId, prdCardId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{prdCardId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("prdCardId") String prdCardId) {
		ReqResult<WorkFlowData> result = WorkFlowDataPrdCardRecordHandler.getInstance().delete(workFlowDataId, prdCardId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PrdCardAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPrdCardRecordHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PrdCardAddListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPrdCardRecordHandler.getInstance().addList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> updList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PrdCardUpdListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPrdCardRecordHandler.getInstance().updList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
