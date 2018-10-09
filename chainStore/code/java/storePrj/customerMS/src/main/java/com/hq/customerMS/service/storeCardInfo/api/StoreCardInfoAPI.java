package com.hq.customerMS.service.storeCardInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeCardInfo.bs.StoreCardInfoHandler;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;

@RestController
@RequestMapping(value = "/storeCardInfo")
public class StoreCardInfoAPI {

	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreCardInfo>> getStoreCardInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreCardInfo> result = StoreCardInfoHandler.getInstance().getStoreCardInfo(storeId);
		ResponseEntity<RestResp<StoreCardInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
