package com.hq.customerMS.service.storeLeaguerInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoHandler;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;

@RestController
@RequestMapping(value = "/storeLeaguerInfo")
public class StoreLeaguerInfoAPI {
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreLeaguerInfo>> getStoreLeaguerInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreLeaguerInfo> result = StoreLeaguerInfoHandler.getInstance().get(storeId);
		ResponseEntity<RestResp<StoreLeaguerInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
