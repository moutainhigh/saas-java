package com.hq.storeMS.service.storeGoods.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsHandler;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;

@RestController
@RequestMapping(value = "/storeGoods")
public class StoreGoodsAPI {

	@RequestMapping(value = "/findSimpleStoreInfo/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreGoods>> findSimpleStoreInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreGoods> result = StoreGoodsHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreGoods>> getStoreGoods(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreGoods> result = StoreGoodsHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreGoods>> updateStoreGoods(
			@PathVariable("storeId") long storeId,
			@RequestBody StoreGoodsUpdateForm updateForm) {
		ReqResult<StoreGoods> result = StoreGoodsHandler.getInstance().update(storeId, updateForm);
		ResponseEntity<RestResp<StoreGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}



}
