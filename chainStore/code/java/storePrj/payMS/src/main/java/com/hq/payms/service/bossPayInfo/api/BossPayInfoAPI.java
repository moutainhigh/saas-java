package com.hq.payms.service.bossPayInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoQueryForm;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoHandler;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoCount;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RestResp;

@RestController
@RequestMapping(value = "/bossPayInfo" )
public class BossPayInfoAPI {
	
	@RequestMapping(value = "/" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<BossPayInfo>> add(@RequestBody BossPayInfoAddApiForm addForm) {  
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().add(addForm);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BossPayInfo>> get(@PathVariable("id") long id) {  
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().get(id);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfo>> update(
			@PathVariable("id") long id,
			@RequestBody BossPayInfoAddApiForm updateForm) {
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().update(id, updateForm);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findByStoreId", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfo>> findByStoreId(@RequestParam(value="storeId",required=true)long storeId) {
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().findByStoreId(storeId);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfo>> findList(
			@RequestParam(value="storeId",required=false, defaultValue="-1") long storeId,
			@RequestParam(value="wxpayAppId",required=false, defaultValue="") String wxpayAppId,
			@RequestParam(value="alipayAppId",required=false, defaultValue="") String alipayAppId,
			@RequestParam(value="pageItemCount",required=true) int pageItemCount,
			@RequestParam(value="pageNo",required=true) int pageNo) {
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setWxpayAppId(wxpayAppId);
		params.setAlipayAppId(alipayAppId);
		params.setPageItemCount(pageItemCount);
		params.setPageNo(pageNo);
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().findList(params);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfoCount>> getCount(
			@RequestParam(value="storeId",required=false, defaultValue="-1") long storeId,
			@RequestParam(value="wxpayAppId",required=false, defaultValue="") String wxpayAppId,
			@RequestParam(value="alipayAppId",required=false, defaultValue="") String alipayAppId){
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setWxpayAppId(wxpayAppId);
		params.setAlipayAppId(alipayAppId);
		ReqResult<BossPayInfoCount> result = BossPayInfoHandler.getInstance().getCount(params);
		ResponseEntity<RestResp<BossPayInfoCount>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	
}
