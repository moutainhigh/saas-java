package com.hq.storeMS.service.storeLeaguerInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoHandler;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;

@RestController
@RequestMapping(value = "/storeLeaguerInfo")
public class StoreLeaguerInfoAPI {
	
	//获取简版的店铺客户信息
	@RequestMapping(value = "/findSimpleStoreInfo/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreLeaguerInfo>> findSimpleStoreInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreLeaguerInfo> result = StoreLeaguerInfoHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreLeaguerInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	//旧版本的数据获取方式
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreLeaguerInfo>> getStoreLeaguerInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreLeaguerInfo> result = StoreLeaguerInfoHandler.getInstance().findSimpleStoreInfo(storeId);
		ResponseEntity<RestResp<StoreLeaguerInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreLeaguerInfo>> updateStoreLeaguerInfo(
			@PathVariable("storeId") long storeId,
			@RequestBody StoreLeaguerInfoUpdateApiForm inputForm) {
		ReqResult<StoreLeaguerInfo> result = StoreLeaguerInfoHandler.getInstance().updateStoreLeaguerInfo(inputForm);
		ResponseEntity<RestResp<StoreLeaguerInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
