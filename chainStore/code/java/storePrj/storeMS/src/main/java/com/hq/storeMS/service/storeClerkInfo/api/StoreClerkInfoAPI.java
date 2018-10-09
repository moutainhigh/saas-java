package com.hq.storeMS.service.storeClerkInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoHandler;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;

@RestController
@RequestMapping(value = "/storeClerkInfo")
public class StoreClerkInfoAPI {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreClerkInfo>> getStoreClerkInfo(@PathVariable("id") String id) {
		ReqResult<StoreClerkInfo> result = StoreClerkInfoHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreClerkInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreClerkInfo>> updateStoreClerkInfo(
			@RequestBody StoreClerkInfoUpdateForm storeClerkInfoForm) {
		ReqResult<StoreClerkInfo> result = StoreClerkInfoHandler.getInstance().update(storeClerkInfoForm);
		ResponseEntity<RestResp<StoreClerkInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/addClerk", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<StoreClerkInfo>> addClerk(@PathVariable("storeId") Long storeId,
			@RequestBody AddClerkInfoData addClerkInfoApiForm) {
		ReqResult<StoreClerkInfo> result = StoreClerkInfoHandler.getInstance().addClerk(storeId, addClerkInfoApiForm);
		ResponseEntity<RestResp<StoreClerkInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
