package com.hq.customerMS.service.storeConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeConfig.bs.StoreConfigHandler;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;

@RestController
@RequestMapping(value = "/storeConfig")
public class StoreConfigAPI {
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreConfig>> getStoreConfig(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreConfig> result = StoreConfigHandler.getInstance().getByStoreId(storeId);
		ResponseEntity<RestResp<StoreConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
