package com.hq.customerMS.service.storeProductInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeProductInfo.bs.StoreProductInfoHandler;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;

@RestController
@RequestMapping(value = "/storeProductInfo" )
public class StoreProductInfoAPI {
	@RequestMapping(value = "/{storeId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreProductInfo>> getStoreProductInfo(@PathVariable("storeId") long storeId){  
		ReqResult<StoreProductInfo> result = StoreProductInfoHandler.getInstance().getStoreProductInfo(storeId);
		ResponseEntity<RestResp<StoreProductInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
