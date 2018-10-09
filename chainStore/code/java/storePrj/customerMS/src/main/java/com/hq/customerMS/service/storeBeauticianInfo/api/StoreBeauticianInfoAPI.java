package com.hq.customerMS.service.storeBeauticianInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storeBeauticianInfo.bs.StoreBeauticianInfoHandler;
import com.hq.storeClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;

@RestController
@RequestMapping(value = "/storeBeauticianInfo" )
public class StoreBeauticianInfoAPI {
	@RequestMapping(value = "/{storeId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreBeauticianInfo>> getStoreBeauticianInfo(@PathVariable("storeId") long storeId){  
		ReqResult<StoreBeauticianInfo> result = StoreBeauticianInfoHandler.getInstance().getStoreBeauticianInfo(storeId);
		ResponseEntity<RestResp<StoreBeauticianInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
