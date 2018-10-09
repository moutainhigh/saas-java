package com.hq.storeMS.service.bonusRecord.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordListForm;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.hq.storeMS.service.bonusRecord.bs.BonusRecordHandler;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/bonusRecord")
public class BonusRecordAPI {
	
	@RequestMapping(value = "/" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<BonusRecord>> saveBonusRecord(
			@RequestBody BonusRecordForm inputForm){
		ReqResult<BonusRecord> result = BonusRecordHandler.getInstance().saveBonusRecord(inputForm);
		ResponseEntity<RestResp<BonusRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/saveList" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<BonusRecord>> saveList(
			@RequestBody BonusRecordListForm inputForm){
		ReqResult<BonusRecord> result = BonusRecordHandler.getInstance().saveList(inputForm);
		ResponseEntity<RestResp<BonusRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{bonusRecordId}" ,method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<RestResp<BonusRecord>> deleteBonusRecord(
			@PathVariable("bonusRecordId") long bonusRecordId){
		ReqResult<BonusRecord> result = BonusRecordHandler.getInstance().deleteBonusRecord(0L, bonusRecordId);
		ResponseEntity<RestResp<BonusRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{bonusRecordId}" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<BonusRecord>> getBonusRecord(
			@PathVariable("bonusRecordId") long bonusRecordId){
		ReqResult<BonusRecord> result = BonusRecordHandler.getInstance().getBonusRecord(0L, bonusRecordId);
		ResponseEntity<RestResp<BonusRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findBonusRecordList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BonusRecord>> findBonusRecordList(
    		@RequestParam(value = "maxTime", defaultValue="0")long maxTime,
    		@RequestParam(value = "minTime", defaultValue="0")long minTime,
    		@RequestParam(value = "orderId", defaultValue="0")long orderId,
    		@RequestParam(value = "status", defaultValue="")String status,
    		@RequestParam(value = "buserName", defaultValue="")String buserName,
    		@RequestParam(value = "storeId")long storeId,
    		@RequestParam(value = "pageItemCount", defaultValue="0")int pageItemCount,
    		@RequestParam(value = "pageNo", defaultValue="1")int pageNo){
		BonusRecordQueryForm queryForm = BonusRecordQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setOrderId(orderId)
			.setStatus(status).setStoreId(storeId).setBuserName(buserName)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<BonusRecord> result = BonusRecordHandler.getInstance().findBonusRecordList(queryForm);
		ResponseEntity<RestResp<BonusRecord>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findBonusRecordPageInfo" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<PageResp>> findBonusRecordPageInfo(
			@RequestParam(value = "maxTime", defaultValue="0")long maxTime,
			@RequestParam(value = "minTime", defaultValue="0")long minTime,
			@RequestParam(value = "orderId", defaultValue="0")long orderId,
			@RequestParam(value = "status", defaultValue="")String status,
			@RequestParam(value = "buserName", defaultValue="")String buserName,
			@RequestParam(value = "storeId")long storeId,
			@RequestParam(value = "pageItemCount", defaultValue="0")int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue="1")int pageNo){
		BonusRecordQueryForm queryForm = BonusRecordQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setOrderId(orderId)
			.setStatus(status).setStoreId(storeId).setBuserName(buserName)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = BonusRecordHandler.getInstance().findBonusRecordPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findBonusRecordGroupPageInfo" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<PageResp>> findBonusRecordGroupPageInfo(
			@RequestParam(value = "maxTime", defaultValue="0")long maxTime,
			@RequestParam(value = "minTime", defaultValue="0")long minTime,
			@RequestParam(value = "orderId", defaultValue="0")long orderId,
			@RequestParam(value = "status", defaultValue="")String status,
			@RequestParam(value = "buserName", defaultValue="")String buserName,
			@RequestParam(value = "storeId")long storeId,
			@RequestParam(value = "pageItemCount", defaultValue="0")int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue="1")int pageNo){
		BonusRecordQueryForm queryForm = BonusRecordQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setOrderId(orderId)
		.setStatus(status).setStoreId(storeId).setBuserName(buserName)
		.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = BonusRecordHandler.getInstance().findBonusRecordGroupPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
