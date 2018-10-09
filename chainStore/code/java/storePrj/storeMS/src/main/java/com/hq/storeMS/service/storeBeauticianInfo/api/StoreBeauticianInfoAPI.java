package com.hq.storeMS.service.storeBeauticianInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateForm;
import com.hq.storeMS.service.storeBeauticianInfo.bs.StoreBeauticianInfoHandler;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfo;

@RestController
@RequestMapping(value = "/storeBeauticianInfo")
public class StoreBeauticianInfoAPI {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreBeauticianInfo>> getStoreBeauticianInfo(@PathVariable("id") long id) {
		ReqResult<StoreBeauticianInfo> result = StoreBeauticianInfoHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreBeauticianInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreBeauticianInfo>> updateStoreBeauticianInfo(
			@RequestBody StoreBeauticianInfoUpdateForm storeBeauticianInfoForm) {
		ReqResult<StoreBeauticianInfo> result = StoreBeauticianInfoHandler.getInstance().update(storeBeauticianInfoForm);
		ResponseEntity<RestResp<StoreBeauticianInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
