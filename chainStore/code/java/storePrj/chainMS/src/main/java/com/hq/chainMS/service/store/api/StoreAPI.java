package com.hq.chainMS.service.store.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.store.bs.StoreHandler;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.data.Store;

@RestController
@RequestMapping(value = "/store")
public class StoreAPI {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findStoreByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findStoreByCond(
			@RequestParam(value = "chainId", defaultValue = "0") long chainId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "storeIds", defaultValue = "") Set<Long> storeIds,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		StoreQueryForm queryForm = StoreQueryForm.newInstance();
		queryForm.setChainId(chainId).setName(name).setStoreIds(storeIds).setPageItemCount(pageItemCount)
				.setPageNo(pageNo);
		ReqResult<PageResp> result = StoreHandler.getInstance().findStoreByCond(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Store>> get(@PathVariable("storeId") long storeId) {
		ReqResult<Store> result = StoreHandler.getInstance().get(storeId);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
