package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataLeaguerInfoHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/leaguerInfo")
public class WorkFlowDataLeaguerInfoAPI {
	@RequestMapping(value = "/{workFlowDataId}/{leaguerId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("leaguerId") String leaguerId,
			@RequestBody LeaguerInfoForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataLeaguerInfoHandler.getInstance().update(workFlowDataId, leaguerId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody LeaguerInfoForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataLeaguerInfoHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
