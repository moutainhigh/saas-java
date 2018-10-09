package com.hq.storeMS.service.storeVip.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeVip.bs.StoreVipHandler;
import com.hq.storeMS.service.storeVip.data.StoreVip;

@RestController
@RequestMapping(value = "/storeVip")
public class StoreVipAPI {

	@RequestMapping(value = "/checkExpired", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreVip>> checkExpired(@RequestParam("storeId") long storeId) {
		ReqResult<StoreVip> result = StoreVipHandler.getInstance().checkExpired(storeId);
		ResponseEntity<RestResp<StoreVip>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
