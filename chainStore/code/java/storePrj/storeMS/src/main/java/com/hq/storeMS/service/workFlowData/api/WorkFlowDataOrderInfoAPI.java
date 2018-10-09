package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.OrderInfoForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataOrderInfoHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/orderInfo")
public class WorkFlowDataOrderInfoAPI {
	@RequestMapping(value = "/{workFlowDataId}/{orderId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("orderId") long orderId,
			@RequestBody OrderInfoForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataOrderInfoHandler.getInstance().update(workFlowDataId, orderId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody OrderInfoForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataOrderInfoHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
