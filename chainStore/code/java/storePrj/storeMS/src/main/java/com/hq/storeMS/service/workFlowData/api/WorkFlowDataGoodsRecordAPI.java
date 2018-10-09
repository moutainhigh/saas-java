package com.hq.storeMS.service.workFlowData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataGoodsRecordHandler;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;

@RestController
@RequestMapping(value = "/workFlowData/goods")
public class WorkFlowDataGoodsRecordAPI {
	@RequestMapping(value = "/{workFlowDataId}/{goodsId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> update(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("goodsId") String goodsId,
			@RequestBody GoodsRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataGoodsRecordHandler.getInstance().update(workFlowDataId, goodsId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{workFlowDataId}/{goodsId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> delete(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@PathVariable("goodsId") String goodsId) {
		ReqResult<WorkFlowData> result = WorkFlowDataGoodsRecordHandler.getInstance().delete(workFlowDataId, goodsId);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> add(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody GoodsRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataGoodsRecordHandler.getInstance().add(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/addList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> addList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody GoodsRecordAddListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataGoodsRecordHandler.getInstance().addList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updList/{workFlowDataId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WorkFlowData>> updList(
			@PathVariable("workFlowDataId") long workFlowDataId,
			@RequestBody GoodsRecordUpdListForm inputForm) {
		ReqResult<WorkFlowData> result = WorkFlowDataGoodsRecordHandler.getInstance().updList(workFlowDataId, inputForm);
		ResponseEntity<RestResp<WorkFlowData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
