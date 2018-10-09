package com.hq.customerMS.service.storeClerkInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeClerkInfo.bs.StoreClerkInfoHandler;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;

@RestController
@RequestMapping(value = "/storeClerkInfo")
public class StoreClerkInfoAPI {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreClerkInfo>> get(@PathVariable("id") String id) {
		ReqResult<StoreClerkInfo> result = StoreClerkInfoHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreClerkInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
