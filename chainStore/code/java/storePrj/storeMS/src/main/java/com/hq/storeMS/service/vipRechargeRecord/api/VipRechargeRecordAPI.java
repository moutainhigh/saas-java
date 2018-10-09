package com.hq.storeMS.service.vipRechargeRecord.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.vipRechargeRecord.bs.VipRechargeRecordHandler;
import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecord;

@RestController
@RequestMapping(value = "/vipRechargeRecord")
public class VipRechargeRecordAPI {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<VipRechargeRecord>> get(@PathVariable("id") long id) {
		ReqResult<VipRechargeRecord> result = VipRechargeRecordHandler.getInstance().get(id);
		ResponseEntity<RestResp<VipRechargeRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<VipRechargeRecord>> findPage(@RequestParam("phone") int phone,
			@RequestParam("minTime") long minTime,
			@RequestParam("maxTime") long maxTime,
			@RequestParam("pageItemCount") int pageItemCount,
			@RequestParam("pageNo") int pageNo) {
		ReqResult<VipRechargeRecord> result = VipRechargeRecordHandler.getInstance().findPage(phone,minTime,maxTime,pageItemCount, pageNo);
		ResponseEntity<RestResp<VipRechargeRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
