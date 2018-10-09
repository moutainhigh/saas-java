package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataBonusInfoHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/bonusInfo")
public class WorkFlowDataBonusInfoAPI {
	@RequestMapping(value = "/{workFlowDataId}/{bonusId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("bonusId") String bonusId,
			@RequestBody BonusInfoUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataBonusInfoHandler.getInstance().update(workFlowDataId, bonusId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{bonusId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("bonusId") String bonusId) {
		ReqResult<WorkFlowData> result = WorkFlowDataBonusInfoHandler.getInstance().delete(workFlowDataId, bonusId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody BonusInfoAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataBonusInfoHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody BonusInfoAddListForm inputForms) {
		ReqResult<WorkFlowData> result = WorkFlowDataBonusInfoHandler.getInstance().addList(workFlowDataId, inputForms);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
