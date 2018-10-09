package com.hq.storeMS.service.billRecord.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.billRecord.apiData.BillRecordAddForm;
import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.hq.storeMS.service.billRecord.bs.BillRecordHandler;
import com.hq.storeMS.service.billRecord.data.BillRecord;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value="billRecord")
public class BillRecordAPI {

	@RequestMapping(value = "/" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<BillRecord>> add(
			@RequestBody BillRecordAddForm addForm){
		ReqResult<BillRecord> result = BillRecordHandler.getInstance().add(addForm);
		ResponseEntity<RestResp<BillRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<BillRecord>> get(
			@PathVariable("id") long id){
		ReqResult<BillRecord> result = BillRecordHandler.getInstance().get(0L, id);
		ResponseEntity<RestResp<BillRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BillRecord>> findList(
    		@RequestParam(value = "storeId")long storeId,
    		@RequestParam(value="tradeNoOrName", defaultValue="") String tradeNoOrName,
			@RequestParam(value = "orderId", defaultValue="0")long orderId,
			@RequestParam(value = "leaguerId", defaultValue="")String leaguerId,
			@RequestParam(value = "payType", defaultValue="")String payType,
			@RequestParam(value = "billType", defaultValue="-1")int billType,
			@RequestParam(value = "state", defaultValue="-1")int state,
			@RequestParam(value = "maxTime", defaultValue="0")long maxTime,
			@RequestParam(value = "minTime", defaultValue="0")long minTime,
			@RequestParam(value = "pageItemCount", defaultValue="0")int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue="1")int pageNo){
		BillRecordQueryForm queryForm = BillRecordQueryForm.newInstance();
		queryForm.setStoreId(storeId).setTradeNoOrName(tradeNoOrName)
		.setOrderId(orderId).setLeaguerId(leaguerId)
		.setPayType(payType).setBillType(billType).setState(state)
		.setMaxTime(maxTime).setMinTime(minTime)
		.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<BillRecord> result = BillRecordHandler.getInstance().findList(queryForm);
		ResponseEntity<RestResp<BillRecord>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPageInfo" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<PageResp>> findPageInfo(
			@RequestParam(value = "storeId")long storeId,
			@RequestParam(value="tradeNoOrName", defaultValue="") String tradeNoOrName,
			@RequestParam(value = "orderId", defaultValue="0")long orderId,
			@RequestParam(value = "leaguerId", defaultValue="")String leaguerId,
			@RequestParam(value = "payType", defaultValue="")String payType,
			@RequestParam(value = "billType", defaultValue="-1")int billType,
			@RequestParam(value = "state", defaultValue="-1")int state,
			@RequestParam(value = "maxTime", defaultValue="0")long maxTime,
			@RequestParam(value = "minTime", defaultValue="0")long minTime,
			@RequestParam(value = "pageItemCount", defaultValue="0")int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue="1")int pageNo){
		BillRecordQueryForm queryForm = BillRecordQueryForm.newInstance();
		queryForm.setStoreId(storeId).setTradeNoOrName(tradeNoOrName)
		.setOrderId(orderId).setLeaguerId(leaguerId)
		.setPayType(payType).setBillType(billType).setState(state)
		.setMaxTime(maxTime).setMinTime(minTime)
		.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = BillRecordHandler.getInstance().findPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
}
