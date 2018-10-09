package com.hq.storeMS.service.storeIncomePay.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateForm;
import com.hq.storeMS.service.storeIncomePay.bs.StoreIncomePayHandler;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePay;

@RestController
@RequestMapping(value = "/storeIncomePay")
public class StoreIncomePayAPI {
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreIncomePay>> getStoreIncomePay(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreIncomePay> result = StoreIncomePayHandler.getInstance().get(storeId);
		ResponseEntity<RestResp<StoreIncomePay>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreIncomePay>> updateStoreIncomePay(
			@PathVariable("storeId") long storeId,
			@RequestBody StoreIncomePayUpdateForm updateForm) {
		ReqResult<StoreIncomePay> result = StoreIncomePayHandler.getInstance().update(storeId, updateForm);
		ResponseEntity<RestResp<StoreIncomePay>> respEntity = result.buildRespEntity();
		return respEntity;
	}



}
