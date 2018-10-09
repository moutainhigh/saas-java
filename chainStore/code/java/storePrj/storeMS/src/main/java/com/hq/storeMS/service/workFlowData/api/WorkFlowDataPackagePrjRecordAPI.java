package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataPackagePrjRecordHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/packagePrjRecord")
public class WorkFlowDataPackagePrjRecordAPI {
	
	@RequestMapping(value = "/{workFlowDataId}/{packagePrjId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("packagePrjId") String packagePrjId,
			@RequestBody PackagePrjRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPackagePrjRecordHandler.getInstance().update(workFlowDataId, packagePrjId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{packagePrjId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("packagePrjId") String packagePrjId) {
		ReqResult<WorkFlowData> result = WorkFlowDataPackagePrjRecordHandler.getInstance().delete(workFlowDataId, packagePrjId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PackagePrjRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPackagePrjRecordHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PackagePrjRecordAddListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPackagePrjRecordHandler.getInstance().addList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> updList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody PackagePrjRecordUpdListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataPackagePrjRecordHandler.getInstance().updList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
