package com.hq.storeMS.service.storeProductInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoHandler;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;

@RestController
@RequestMapping(value = "/storeProductInfo")
public class StoreProductInfoAPI {

	@RequestMapping(value = "/findSimpleStoreInfo/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreProductInfo>> findSimpleStoreInfo(@PathVariable("storeId") long storeId) {
		ReqResult<StoreProductInfo> result = StoreProductInfoHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreProductInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreProductInfo>> getStoreProductInfo(@PathVariable("storeId") long storeId) {
		ReqResult<StoreProductInfo> result = StoreProductInfoHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreProductInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreProductInfo>> updateStoreProductInfo(
			@RequestBody StoreProductInfoUpdateForm updateForm) {
		ReqResult<StoreProductInfo> result = StoreProductInfoHandler.getInstance().update(updateForm);
		ResponseEntity<RestResp<StoreProductInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
