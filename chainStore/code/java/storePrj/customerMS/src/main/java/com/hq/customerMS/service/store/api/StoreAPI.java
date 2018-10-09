package com.hq.customerMS.service.store.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.store.bs.StoreHandler;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.data.Store;

@RestController
@RequestMapping(value = "/store" )
public class StoreAPI {
	
	@RequestMapping(value = "/joinStore", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Store>> joinStore(@RequestBody JoinStoreForm joinStoreForm) {
		ReqResult<Store> result = StoreHandler.getInstance().joinStore(joinStoreForm);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findMyStores" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<Store>> findByCuser(@RequestParam("cuserId") long cuserId){
		ReqResult<Store> result = StoreHandler.getInstance().findMyStores(cuserId);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/{storeId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<Store>> getStore(@PathVariable("storeId") long storeId){
		ReqResult<Store> result = StoreHandler.getInstance().getStore(storeId);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
