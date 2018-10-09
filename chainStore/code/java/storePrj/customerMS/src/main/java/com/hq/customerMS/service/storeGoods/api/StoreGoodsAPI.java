package com.hq.customerMS.service.storeGoods.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeGoods.bs.StoreGoodsHandler;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;

@RestController
@RequestMapping(value = "/storeGoods")
public class StoreGoodsAPI {
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreGoods>> getStoreGoods(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreGoods> result = StoreGoodsHandler.getInstance().get(storeId);
		ResponseEntity<RestResp<StoreGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
